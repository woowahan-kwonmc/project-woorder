<template>
    <div>
        <need-login v-if="!this.$store.state.isLoggedIn"/>
        <v-col>
            <v-toolbar>
                <v-toolbar-title color="black" flat>메뉴</v-toolbar-title>
            </v-toolbar>
            <v-container>
                <v-row>
                    <v-col :key="i" cols="12" v-for="(menu, i) in order.menus">
                        <v-card dark>
                            <div style="display: flex; flex-direction: row;">
                                <div class="d-flex flex-no-wrap justify-space-between" style="flex: 5;">
                                    <div>
                                        <v-card-title v-text="menu.menuName"></v-card-title>
                                        <v-card-subtitle v-text="`${menu.menuPrice} 원`"></v-card-subtitle>
                                    </div>
                                </div>
                                <div style="flex: 1; flex-direction: column; display: flex; justify-content: center;">
                                    <div style="display: flex; flex-direction: row">
                                        <v-btn :disabled="order.activate" @click="addItemBtnClickHandler(menu)"
                                               class="mx-2" color="primary" dark
                                               fab
                                               small>
                                            <v-icon dark>mdi-plus</v-icon>
                                        </v-btn>
                                        <v-btn :disabled="order.activate" @click="removeItemBtnClickHandler(menu)"
                                               class="mx-2" color="primary" dark
                                               fab
                                               small>
                                            <v-icon dark>mdi-minus</v-icon>
                                        </v-btn>
                                    </div>
                                </div>
                            </div>
                        </v-card>
                    </v-col>
                </v-row>
            </v-container>
        </v-col>
        <v-container>
            <v-expansion-panels popout v-model="focused">
                <!--  메뉴를 선택한다  -->
                <v-expansion-panel :disabled="order.activate">
                    <v-expansion-panel-header>장바구니</v-expansion-panel-header>
                    <v-expansion-panel-content>
                        <order-basket :order="order"/>
                        <div style="padding-top: 8px; padding-bottom: 8px;">
                            <v-btn :disabled="order.activate" @click="doOrder" color="black" dark
                                   width="100%">주문하기
                            </v-btn>
                        </div>
                    </v-expansion-panel-content>
                </v-expansion-panel>
                <!--  주문 정보를 생성한다.  -->
                <v-expansion-panel :disabled="payTarget.activate">
                    <v-expansion-panel-header>주문 정보</v-expansion-panel-header>
                    <v-expansion-panel-content>
                        <pay-basket :pay-target="payTarget"/>
                        <div style="padding-top: 8px; padding-bottom: 8px; display: flex; flex-direction: column;"
                             v-if="coupon.appliedCoupons.length !== 0">
                            <div class="default-box-shadow" style="display: flex; flex-direction: column;">
                                <div style="border-bottom: 1px solid gray; text-align: center; padding: 4px;">적용 쿠폰
                                </div>
                                <div :key="i" v-for="(coupon, i) in descAppliedCoupons()">
                                    <div style="display: flex; flex-direction: row; height: 30px">
                                        <div class="applied-coupon-element">
                                            <div>{{coupon.name}}</div>
                                        </div>
                                        <div class="applied-coupon-element">
                                            <div>{{coupon.code ? coupon.code : '다운로드 쿠폰'}}</div>
                                        </div>
                                        <div class="applied-coupon-element">
                                            <div>{{coupon.discount}}</div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div style="display: flex; flex-direction: row; height: 20px; border-top: 1px dashed gray; padding: 8px; margin-bottom: 8px;">
                                <div style="display: flex; flex: 1;">할인 가격</div>
                                <div style="display: flex; flex: 2;">{{totalPrice(order.basket)}}</div>
                            </div>
                        </div>
                        <div style="padding-top: 8px; padding-bottom: 8px; display: flex; flex-direction: row;">
                            <div style="flex: 1; padding-right: 4px;">
                                <v-btn :disabled="payTarget.activate" @click="openCoupons" color="black" dark
                                       width="100%">쿠폰적용하기
                                </v-btn>
                            </div>
                            <div style="flex: 1; padding-left: 4px;">
                                <v-btn :disabled="payTarget.activate" @click="checkPayInfo" color="black" dark
                                       width="100%">결제하기
                                </v-btn>
                            </div>
                        </div>
                    </v-expansion-panel-content>
                </v-expansion-panel>
                <!--  결제 정보를 생성한다.  -->
                <v-expansion-panel :disabled="pay.activate">
                    <v-expansion-panel-header>결제 정보</v-expansion-panel-header>
                    <v-expansion-panel-content>
                        <div>결제 금액 - {{coupon.discountPrice}} 원</div>
                        <div>
                            <v-select
                                    :items="pay.types"
                                    label="결제수단"
                                    v-model="pay.selectedType"
                            ></v-select>
                        </div>
                        <div>
                            <v-btn :disabled="pay.activate" @click="doPay" color="black" dark width="100%">결제하기</v-btn>
                        </div>
                    </v-expansion-panel-content>
                </v-expansion-panel>
                <v-expansion-panel :disabled="result.activate">
                    <!--  완료되었음을 보여준다.  -->
                    <v-expansion-panel-header>완료</v-expansion-panel-header>
                    <v-expansion-panel-content>
                        <div>결제 수단 - {{this.result.payment ? this.result.payment.method : ''}}</div>
                        <div>결제 상태 - {{this.result.payment ? this.result.payment.status : ''}}</div>
                        <div>결제 금액 - {{this.result.payment ? this.result.payment.price : ''}}</div>
                        <div>
                            <v-btn :disabled="result.activate" @click="initPage" color="black" dark width="100%">주문 끗~
                            </v-btn>
                        </div>
                    </v-expansion-panel-content>
                </v-expansion-panel>
            </v-expansion-panels>
        </v-container>
        <v-dialog max-width="300px" scrollable v-model="coupon.dialog">
            <v-card>
                <v-card-title>적용할 쿠폰 선택</v-card-title>
                <v-divider></v-divider>
                <v-card-text style="height: 300px;">
                    <div style="text-align: center; padding: 24px;" v-if="coupon.memberCoupons">
                        쿠폰이 없습니다.
                    </div>
                    <div :key="i" v-for="(memberCoupon, i) in coupon.memberCoupons">
                        <v-checkbox :label="memberCoupon.name" :value="memberCoupon.no"
                                    v-model="coupon.appliedCoupons"></v-checkbox>
                        <div style="display: flex; flex-direction: column; font-size: 14px; text-align: right;">
                            <div>{{memberCoupon.code ? memberCoupon.code : '다운로드 쿠폰'}}</div>
                            <div>{{memberCoupon.discount}}</div>
                        </div>
                    </div>
                </v-card-text>
                <v-divider></v-divider>
                <v-card-actions>
                    <v-btn @click="closeCoupons" color="blue darken-1" text>전체 취소</v-btn>
                    <v-btn @click="applyCoupons" color="blue darken-1" text>선택</v-btn>
                </v-card-actions>
            </v-card>
        </v-dialog>
    </div>
</template>

<script>
    import NeedLogin from '../components/NeedLogin'
    import OrderBasket from "../components/OrderBasket";
    import PayBasket from "../components/PayBasket";
    import {menuController} from "../common/controller/MenuController";
    import {Order} from "../common/domain/Order";
    import {orderController} from "../common/controller/OrderController";
    import {couponController} from "../common/controller/CouponController";
    import {paymentController} from "../common/controller/PaymentController";

    export default {
        data: () => ({
            steps: ['장바구니', '주문 정보', '결제 정보', '완료'],
            focused: null,
            page: 1,
            num: 10,
            order: {
                menus: [],
                basket: new Map(),
                size: 0,
                activate: false,
            },
            payTarget: {
                orders: null,
                activate: true,
            },
            coupon: {
                dialog: false,
                memberCoupons: null,
                appliedCoupons: [],
                discountPrice: 0,
                useCoupons: null,
            },
            pay: {
                activate: true,
                types: ['CASH', 'CARD', 'KAKAO_PAY', 'BAEMIN_PAY', 'TOSS_PAY'],
                selectedType: null,
            },
            result: {
                activate: true,
                payment: null,
            }
        }),

        components: {
            NeedLogin,
            OrderBasket,
            PayBasket,
        },

        async beforeMount() {
            try {
                const response = await menuController.selectPageMenus(this.page, this.num)
                this.order.menus = response.data.menus
            } catch (e) {
                // eslint-disable-next-line no-console
                console.log(e)
            }
        },

        methods: {
            addItemBtnClickHandler(menu) {
                this.order.size++
                if (this.order.basket.has(menu)) {
                    this.order.basket.set(menu, this.order.basket.get(menu) + 1)
                    return
                }
                this.order.basket.set(menu, 1)
            },

            removeItemBtnClickHandler(menu) {
                this.order.size--
                if (!this.order.basket.has(menu)) {
                    return;
                }
                if (this.order.basket.get(menu) === 1) {
                    this.order.basket.delete(menu)
                    return
                }
                this.order.basket.set(menu, this.order.basket.get(menu) - 1)
            },

            async doOrder() {
                if (this.order.basket.size === 0) {
                    return
                }
                const memberNo = this.$store.state.member.memberNo
                const tmpOrders = Array.from(this.order.basket)
                    .map(([key, value]) => new Order(key.menuNo, memberNo, value))
                const orders = tmpOrders
                    .map(order => ({
                        menuNo: order.menuNo,
                        memberNo: order.memberNo,
                        quantity: order.quantity
                    }))
                try {
                    const response = await orderController.createOrder({orders})
                    this.payTarget.orders = response.data.orders
                    this.order.activate = true;
                    this.payTarget.activate = false;
                    this.focused = 1
                } catch (e) {
                    // eslint-disable-next-line no-console
                    console.log(e.response.data)
                }
            },

            applyCoupons() {
                this.coupon.dialog = false
                // eslint-disable-next-line no-console
                console.log(this.coupon.appliedCoupons)
            },

            closeCoupons() {
                this.coupon.dialog = false
                this.coupon.appliedCoupons = []
            },

            descAppliedCoupons() {
                const useCoupons = this.coupon.appliedCoupons.map(this.getCouponByNo);
                this.coupon.useCoupons = useCoupons
                return useCoupons
            },

            getCouponByNo(no) {
                return this.coupon.memberCoupons.filter(coupon => coupon.no === no)[0]
            },

            async openCoupons() {
                this.coupon.dialog = true
                const memberNo = this.$store.state.member.memberNo
                try {
                    const response = await couponController.findMemberCoupons(memberNo)
                    this.coupon.memberCoupons = response.data.coupons
                } catch (e) {
                    // eslint-disable-next-line no-console
                    console.log(e)
                }
            },

            totalPrice(orders) {
                this.coupon.discountPrice = this._getDiscountPrice(orders)
                return this.coupon.discountPrice
            },

            _getDiscountPrice(orders) {
                const price = Array.from(orders)
                    .map(([key, value]) => key.menuPrice * value)
                    .reduce((a, b) => a + b, 0)
                const discount = this.descAppliedCoupons()
                    .map(coupon => coupon.discount)
                    .reduce((a, b) => a + b, 0)
                return price - discount;
            },

            checkPayInfo() {
                if (this.coupon.discountPrice === 0) {
                    this.coupon.discountPrice = this._getDiscountPrice(this.order.basket)
                }
                this.payTarget.activate = true;
                this.pay.activate = false;
                this.focused = 2
            },

            async doPay() {
                if (!this.pay.selectedType) {
                    return
                }
                const memberNo = this.$store.state.member.memberNo
                const method = this.pay.selectedType
                const ordersNo = this._getOrdersNo(this.payTarget.orders)
                const couponsNo = this.coupon.appliedCoupons
                try {
                    const response = await paymentController.createPayment(method, memberNo, ordersNo, couponsNo)
                    this.result.payment = response.data.payment
                    this.pay.activate = true;
                    this.result.activate = false;
                    this.focused = 3
                } catch (e) {
                    // eslint-disable-next-line no-console
                    console.log(e);
                }
            },

            _getOrdersNo(orders) {
                return orders.map(order => order.no)
            },

            initPage() {
                // this.$router.push('/')
                this.$router.go(this.$router.currentRoute)
            }
        }
    }

</script>

<style>
    .default-box-shadow {
        box-shadow: 0px 2px 4px -1px rgba(0, 0, 0, 0.2), 0px 4px 5px 0px rgba(0, 0, 0, 0.14), 0px 1px 10px 0px rgba(0, 0, 0, 0.12);
    }

    .applied-coupon-element {
        flex: 1;
        text-align: center;
        display: flex;
        flex-direction: column;
        justify-content: center;
    }
</style>
<template>
    <div>
        <need-login v-if="!this.$store.state.isLoggedIn"/>
        <v-col>
            <v-toolbar>
                <v-toolbar-title color="black" flat>코드 쿠폰 등록</v-toolbar-title>
            </v-toolbar>
            <div class="default-box-shadow" style="padding: 16px;">
                <v-text-field label="쿠폰 코드" required v-model="code.coupon"></v-text-field>
                <div style="padding: 16px; text-align: center; color: red;" v-if="this.code.errorMessage">
                    {{this.code.errorMessage}}
                </div>
                <div style="padding: 16px; text-align: left; color: black;" v-if="this.code.enrolledCoupon">
                    <div>쿠폰 이름 - {{this.code.enrolledCoupon.name}}</div>
                    <div>할인 금액 - {{this.code.enrolledCoupon.discount}} 원</div>
                </div>
                <div style="padding-top: 8px; padding-bottom: 8px;">
                    <v-btn @click="enrollCoupon" color="black" dark width="100%">쿠폰 등록</v-btn>
                </div>
            </div>
        </v-col>

        <v-col>
            <v-toolbar>
                <v-toolbar-title color="black" flat>코드 쿠폰 등록</v-toolbar-title>
            </v-toolbar>
            <div class="default-box-shadow" style="padding: 16px;">
                <div>쿠폰 받기</div>
            </div>
        </v-col>
    </div>
</template>

<script>
    import NeedLogin from "../components/NeedLogin";
    import {couponController} from "../common/controller/CouponController";

    export default {
        data: () => ({
            code: {
                coupon: null,
                errorMessage: '',
                enrolledCoupon: null,
            },
        }),

        components: {
            NeedLogin,
        },

        methods: {
            async enrollCoupon() {
                this.code.enrolledCoupon = null
                if (!this.code.coupon) {
                    return
                }
                if (this.code.coupon.length !== 12) {
                    this.code.errorMessage = '쿠폰 코드 길이를 확인해주세요.'
                    return
                }
                // eslint-disable-next-line no-console
                console.log(this.code.coupon)
                try {
                    const memberNo = this.$store.state.member.memberNo
                    const response = await couponController.enrollCodeCoupon(memberNo, this.code.coupon)
                    this.code.errorMessage = null
                    // eslint-disable-next-line no-console
                    console.log(response.data.coupon)
                    this.code.enrolledCoupon = response.data.coupon
                } catch (e) {
                    if (e.response.data) {
                        this.code.errorMessage = e.response.data.message
                        return
                    }
                    // eslint-disable-next-line no-console
                    console.log(e)
                }
            }
        }
    }
</script>

<style>
    .default-box-shadow {
        box-shadow: 0px 2px 4px -1px rgba(0, 0, 0, 0.2), 0px 4px 5px 0px rgba(0, 0, 0, 0.14), 0px 1px 10px 0px rgba(0, 0, 0, 0.12);
    }
</style>
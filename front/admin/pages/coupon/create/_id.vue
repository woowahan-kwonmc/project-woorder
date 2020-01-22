<template xmlns:div="http://www.w3.org/1999/html">
  <wide-template>
    <v-col>
      <v-toolbar>
        <v-toolbar-title>쿠폰 생성</v-toolbar-title>
      </v-toolbar>
      <v-alert style="background-color: red" type="error" v-if="notFound">
        {{ message }}
      </v-alert>
      <div class="default-box-shadow" style="padding: 16px; margin-top: 8px; margin-bottom: 8px;" v-if="couponType">
        <coupon-type-desc :coupon-size-mode="true" :coupon-type="couponType" :coupons-size="couponsSize"/>
      </div>
    </v-col>
    <v-col v-if="couponType">
      <v-toolbar>
        <v-toolbar-title>쿠폰 생성에 필요한 정보 입력</v-toolbar-title>
      </v-toolbar>
      <div class="default-box-shadow" style="padding: 16px; margin-top: 8px; margin-bottom: 8px;">
        <div style="display: flex; flex-direction: row;">
          <div style="flex: 1; display: flex; flex-direction: column">
            <div style="padding-top: 8px; padding-bottom: 8px;">
              현재 생성할 쿠폰 타입 - {{ couponType.hasCode ? '코드 쿠폰' : '다운로드 쿠폰' }}
            </div>
            <div style="padding-top: 8px; padding-bottom: 8px;">생성 가능한 수량 - {{ couponType.count - couponsSize }} 개</div>
          </div>
          <div style="padding-top: 8px; padding-bottom: 8px; flex: 1;">
            <v-text-field filled label="생성할 수량" type="number" v-model="requestCounts"></v-text-field>
          </div>
        </div>
        <div style="color: red; padding-bottom: 16px; text-align: center;" v-if="errorMessage">
          {{errorMessage}}
        </div>
        <div>
          <v-btn color="indigo" dark style="width: 100%" tile v-on:click="createBtnClickHandler">쿠폰 생성</v-btn>
        </div>
      </div>
    </v-col>
    <v-col v-if="createdCoupon.coupons.length !== 0">
      <div class="default-box-shadow" style="padding: 16px; margin-top: 8px; margin-bottom: 8px;">
        <div style="display: flex; flex-direction: column;">
          <div :key="i" style="display: flex; flex-direction: row; height: 50px"
               v-for="(coupon, i) in paginate(createdCoupon.coupons, createdCoupon.page)">
            <div style="flex: 1; flex-direction: column; display: flex; justify-content: center;">
              <div style="text-align: center;">{{coupon.code ? coupon.code : '다운로드 쿠폰은 코드가 없습니다.'}}</div>
            </div>
            <div style="flex: 1; flex-direction: column; display: flex; justify-content: center;">
              <div style="text-align: center;">{{coupon.status}}</div>
            </div>
          </div>
        </div>
        <div style="display: flex; flex-direction: row; margin-top: 16px; justify-content: center;">
          <div style="width: fit-content; display: flex; flex-direction: row;">
            <v-btn dark fab small style="margin-right: 8px;" v-on:click="prevBtnHandler">
              이전
            </v-btn>
            <div style="height: 100%; padding: 8px;">
              {{ this.createdCoupon.page }}
            </div>
            <v-btn dark fab small style="margin-left: 8px;" v-on:click="nextBtnHandler">
              다음
            </v-btn>
          </div>
        </div>
      </div>
    </v-col>
  </wide-template>
</template>

<script>
    import WideTemplate from '../../../components/WideTemplate'
    import CouponTypeDesc from "../../../components/coupontype/CouponTypeDesc";
    import {couponTypeController} from "../../../middleware/controller/CouponTypeController";
    import {couponController} from "../../../middleware/controller/CouponController";

    export default {
        data: () => ({
            couponType: null,
            notFound: false,
            couponsSize: 0,
            message: null,
            requestCounts: 0,
            errorMessage: null,
            createdCoupon: {
                page: 1,
                coupons: [],
                type: null
            },
        }),

        components: {
            WideTemplate,
            CouponTypeDesc,
        },

        async beforeMount() {
            const couponTypeNo = this.$route.params.id;
            if (!couponTypeNo) {
                return
            }
            try {
                const response = await couponTypeController.findById(couponTypeNo)
                const {couponType, couponsSize} = response.data
                this.couponType = couponType
                this.couponsSize = couponsSize
            } catch (e) {
                if (e.response.data) {
                    this.notFound = true
                    this.message = e.response.data.message
                }
                console.log(e.response)
            }
        },

        methods: {
            async createBtnClickHandler() {
                try {
                    const response = await couponController.create(this.couponType.hasCode, this.couponType.no, this.requestCounts)
                    const {coupons, couponType} = response.data
                    this.createdCoupon.coupons = coupons
                    this.createdCoupon.type = couponType
                    this.errorMessage = null
                    this.requestCounts = 0
                } catch (e) {
                    this.errorMessage = e.response.data ? e.response.data.message : '알 수 없는 에러 발생'
                }
            },

            paginate(array, page_number) {
                return array.slice((page_number - 1) * 10, page_number * 10);
            },

            prevBtnHandler() {
                if (this.createdCoupon.page <= 1) {
                    return
                }
                this.createdCoupon.page -= 1
            },

            nextBtnHandler() {
                const totalPage = this.createdCoupon.coupons.length / 10
                if (this.createdCoupon.page >= totalPage) {
                    return
                }
                this.createdCoupon.page += 1
            }
        }
    }
</script>

<style>
  .default-box-shadow {
    box-shadow: 0px 2px 4px -1px rgba(0, 0, 0, 0.2), 0px 4px 5px 0px rgba(0, 0, 0, 0.14), 0px 1px 10px 0px rgba(0, 0, 0, 0.12);
  }
</style>


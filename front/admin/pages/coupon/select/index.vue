<template>
  <wide-template>
    <v-col>
      <v-toolbar>
        <v-toolbar-title>전체 쿠폰 조회</v-toolbar-title>
      </v-toolbar>
      <v-alert style="background-color: red" type="error" v-if="notFound">
        I'm an error alert.
      </v-alert>
    </v-col>
    <v-col>
      <v-toolbar>
        <v-toolbar-title>쿠폰 조회 조건</v-toolbar-title>
      </v-toolbar>
      <div class="default-box-shadow" style="padding: 16px; margin-top: 8px; margin-bottom: 8px;">
        <div style="display: flex; flex-direction: row;">
          <div style="flex: 1;">
            <v-checkbox :label="expired ? '사용 기간 만료' : '사용 가능 기간'" v-model="expired"></v-checkbox>
          </div>
          <div style="flex: 1;">
            <v-checkbox :label="usable ? '사용 완료' : '미사용'" v-model="usable"></v-checkbox>
          </div>
        </div>
        <div style="color: red; padding-bottom: 16px; text-align: center;" v-if="errorMessage">
          {{errorMessage}}
        </div>
        <div>
          <v-btn color="indigo" dark style="width: 100%" tile v-on:click="selectBtnClickHandler">쿠폰 조회</v-btn>
        </div>
      </div>
    </v-col>
    <v-col v-if="selectedCoupon.coupons.length !== 0">
      <v-toolbar>
        <v-toolbar-title>조회된 쿠폰</v-toolbar-title>
      </v-toolbar>
      <div class="default-box-shadow" style="padding: 16px; margin-top: 8px; margin-bottom: 8px;">
        <div style="display: flex; flex-direction: column;">
          <div :key="i" style="display: flex; flex-direction: row; height: 50px"
               v-for="(coupon, i) in selectedCoupon.coupons">
            <div style="flex: 1; flex-direction: column; display: flex; justify-content: center;">
              <div style="text-align: center;">{{coupon.name}}</div>
            </div>
            <div style="flex: 1; flex-direction: column; display: flex; justify-content: center;">
              <div style="text-align: center;">{{coupon.code ? coupon.code : '다운로드 쿠폰'}}</div>
            </div>
            <div style="flex: 1; flex-direction: column; display: flex; justify-content: center;">
              <div style="text-align: center;">{{coupon.useStatus}}</div>
            </div>
            <div style="flex: 1; flex-direction: column; display: flex; justify-content: center;">
              <div style="text-align: center;">{{coupon.discount}}</div>
            </div>
            <div style="flex: 1; flex-direction: column; display: flex; justify-content: center;">
              <div style="text-align: center;">{{coupon.memberName ? coupon.memberName : '소유자 없음'}}</div>
            </div>
          </div>
        </div>
        <div style="display: flex; flex-direction: row; margin-top: 16px; justify-content: center;">
          <div style="width: fit-content; display: flex; flex-direction: row;">
            <v-btn dark fab small style="margin-right: 8px;" v-on:click="prevBtnHandler">
              이전
            </v-btn>
            <div style="height: 100%; padding: 8px;">
              {{ this.selectedCoupon.page }}
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
    import {couponController} from "../../../middleware/controller/CouponController";

    export default {
        data: () => ({
            expired: false,
            usable: false,
            selectedCoupon: {
                page: 1,
                coupons: [],
                type: null
            },
            notFound: false,
            errorMessage: null
        }),

        components: {
            WideTemplate,
            CouponTypeDesc,
        },

        methods: {
            async selectBtnClickHandler() {
                try {
                    const response = await couponController.findCoupons(this.selectedCoupon.page, 10, this.usable, this.expired)
                    this.selectedCoupon.coupons = response.data.coupons
                } catch (e) {
                    console.log(e)
                }
            },

            async prevBtnHandler() {
                if (this.selectedCoupon.page <= 1) {
                    return
                }
                const response = await couponController.findCoupons(this.selectedCoupon.page - 1, 10, this.usable, this.expired)
                const {coupons} = response.data
                if (coupons.length === 0) {
                    return
                }
                this.selectedCoupon.coupons = coupons
                this.selectedCoupon.page -= 1
            },

            async nextBtnHandler() {
                const response = await couponController.findCoupons(this.selectedCoupon.page + 1, 10, this.export, this.usable)
                const {coupons} = response.data
                if (coupons.length === 0) {
                    return
                }
                this.selectedCoupon.coupons = coupons
                this.selectedCoupon.page += 1
            }
        }
    }
</script>

<style>
  .default-box-shadow {
    box-shadow: 0px 2px 4px -1px rgba(0, 0, 0, 0.2), 0px 4px 5px 0px rgba(0, 0, 0, 0.14), 0px 1px 10px 0px rgba(0, 0, 0, 0.12);
  }
</style>


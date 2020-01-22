<template>
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
        ㅇㅇ
      </div>
    </v-col>
  </wide-template>
</template>

<script>
    import WideTemplate from '../../../components/WideTemplate'
    import CouponTypeDesc from "../../../components/coupontype/CouponTypeDesc";
    import {couponTypeController} from "../../../middleware/controller/CouponTypeController";

    export default {
        data: () => ({
            couponType: null,
            notFound: false,
            couponsSize: 0,
            message: null,
        }),

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

        components: {
            WideTemplate,
            CouponTypeDesc,
        },
    }
</script>

<style>
  .default-box-shadow {
    box-shadow: 0px 2px 4px -1px rgba(0, 0, 0, 0.2), 0px 4px 5px 0px rgba(0, 0, 0, 0.14), 0px 1px 10px 0px rgba(0, 0, 0, 0.12);
  }
</style>


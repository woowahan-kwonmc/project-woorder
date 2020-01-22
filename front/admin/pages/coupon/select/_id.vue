<template>
  <wide-template>
    <v-col>
      <v-toolbar>
        <v-toolbar-title>쿠폰 조회</v-toolbar-title>
      </v-toolbar>
      <v-alert style="background-color: red" type="error" v-if="notFound">
        I'm an error alert.
      </v-alert>
      <div style="padding: 16px; box-shadow: 2px 2px 5px 0 rgba(0, 0, 0, 0.3); margin-top: 8px; margin-bottom: 8px;"
           v-if="couponType">
        <coupon-type-desc :coupon-size-mode="true" :coupon-type="couponType" :coupons-size="couponsSize"/>
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
</style>


<template>
  <wide-template>
    <v-col>
      <v-toolbar>
        <v-toolbar-title>쿠폰 타입 조회 결과</v-toolbar-title>
      </v-toolbar>
      <div style="height: 100px;text-align: center; line-height: 100px;
           font-size: 20px; box-shadow: 2px 2px 5px 0 rgba(0, 0, 0, 0.3);
           margin-top: 16px;"
           v-if="couponTypes.length === 0">
        No Data
      </div>
      <v-expansion-panels style="margin-top: 16px;" v-if="couponTypes.length !== 0">
        <v-expansion-panel
          :key="i"
          v-for="(couponType, i) in couponTypes"
        >
          <v-expansion-panel-header>
            <coupon-type-expansion-panel-element :coupon-type="couponType"/>
          </v-expansion-panel-header>
          <v-expansion-panel-content>
            <div style="display: flex; flex-direction: row;">
              <div style="display: flex; flex-direction: column; flex: 10">
                <coupon-type-desc :coupon-size-mode="false" :coupon-type="couponType"/>
              </div>
              <div style="flex: 1; flex-direction: column; justify-content: center; display: flex;">
                <div style="align-self: center; margin-bottom: 16px;">
                  <v-btn color="success" dark fab v-on:click="createCouponBtnHandler(couponType.no)">생성</v-btn>
                </div>
              </div>
            </div>
          </v-expansion-panel-content>
        </v-expansion-panel>
      </v-expansion-panels>
      <div style="display: flex; flex-direction: row; margin-top: 16px; justify-content: center;">
        <div style="width: fit-content;">
          <v-btn dark fab small style="margin-right: 8px;" v-on:click="prevBtnHandler">
            이전
          </v-btn>
          <v-btn dark fab small style="margin-left: 8px;" v-on:click="nextBtnHandler">
            다음
          </v-btn>
        </div>
      </div>
    </v-col>
  </wide-template>
</template>

<script>
    import WideTemplate from "../../components/WideTemplate";
    import CouponTypeDesc from "../../components/coupontype/CouponTypeDesc";
    import CouponTypeExpansionPanelElement from "../../components/coupontype/CouponTypeExpansionPanelElement";
    import {couponTypeController} from "../../middleware/controller/CouponTypeController";


    export default {
        data: () => ({
            page: 1,
            num: 10,
            couponTypes: [],
            message: null,
        }),
        components: {
            WideTemplate,
            CouponTypeDesc,
            CouponTypeExpansionPanelElement
        },
        async beforeMount() {
            try {
                const response = await couponTypeController.select(this.page, 10);
                const {couponTypes} = response.data
                if (couponTypes.length === 0) {
                    return
                }
                this.couponTypes = couponTypes
            } catch (e) {
                console.log(e.response.data.message)
            }
        },
        methods: {
            async prevBtnHandler() {
                if (this.page === 1) {
                    return;
                }
                try {
                    const response = await couponTypeController.select(this.page - 1, this.num)
                    const {couponTypes} = response.data
                    if (couponTypes.length === 0) {
                        return
                    }
                    this.couponTypes = couponTypes
                    this.page -= 1
                } catch (e) {
                    console.log(e.response.data.message)
                }
            },

            async nextBtnHandler() {
                try {
                    const response = await couponTypeController.select(this.page + 1, this.num)
                    const {couponTypes} = response.data
                    if (couponTypes.length === 0) {
                        return
                    }
                    this.couponTypes = couponTypes
                    this.page += 1
                } catch (e) {
                    console.log(e.response.data.message)
                }
            },

            parseLocalTime(stringTime) {
                const date = new Date(stringTime);
                return `${date.getFullYear()}년 ${date.getMonth() + 1}월 ${date.getDate()}일`
            },

            createCouponBtnHandler(id) {
                this.$router.push(`/coupon/create/${id}`)
            }
        }
    };
</script>

<style>
</style>


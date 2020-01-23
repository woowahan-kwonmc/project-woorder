<template>
  <default-template data-app>
    <h1 style="margin-bottom: 16px">쿠폰 타입 생성</h1>
    <div>
      <v-text-field filled label="쿠폰 타입 이름" v-model="couponType.name"></v-text-field>
      <v-text-field filled label="쿠폰 타입 수량" type="number" v-model="couponType.count"></v-text-field>
      <v-text-field filled label="쿠폰 타입 할인 금액" type="number" v-model="couponType.discount"></v-text-field>
      <div style="margin-bottom: 16px">
        <p>현재 시간</p>
        <div>{{ new Date() }}</div>
      </div>
      <v-date-picker range v-model="dates"></v-date-picker>
      <v-text-field
        filled
        label="쿠폰 사용 기간"
        readonly
        style="margin-top: 16px"
        v-model="dateRangeText"
      ></v-text-field>
      <div class="radio-group-box">
        <client-only>
          <v-radio-group row v-model="couponType.hasCode">
            <v-radio label="다운로드 쿠폰 타입" value="false"></v-radio>
            <v-radio label="코드 쿠폰 타입" value="true"></v-radio>
          </v-radio-group>
        </client-only>
      </div>
      <client-only>
        <div style="color: red; margin: 16px">{{ errorMessage }}</div>
      </client-only>
      <div class="button-group-box">
        <v-btn color="success" dark v-on:click="generateCouponType" x-large>쿠폰 타입 생성하기</v-btn>
      </div>
    </div>
    <v-dialog max-width="290" persistent v-model="dialog">
      <v-card>
        <v-card-title class="headline">{{response.message}}</v-card-title>
        <v-card-text>
          쿠폰 타입 No - {{response.no}} <br/>
          쿠폰 타입 이름 - {{response.name}} <br/>
          쿠폰 타입 할인 금액 - {{response.discount}} <br/>
          쿠폰 타입 발급 가능 수 - {{response.count}} <br/>
          쿠폰 타입 이름 - {{response.name}} <br/>
        </v-card-text>
        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn @click="confirmBtnHandler" color="green darken-1" text>확인</v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
  </default-template>
</template>

<script>
    import DefaultTemplate from "../../components/DefaultTemplate"
    import {couponTypeController} from "../../middleware/controller/CouponTypeController"

    export default {
        data: () => ({
            dates: ["", ""],
            couponType: {
                name: null,
                count: null,
                discount: null,
                hasCode: false,
                startTime: null,
                endTime: null
            },
            errorMessage: null,
            dialog: false,
            response: {
                message: null,
                no: null,
                discount: null,
                count: null,
                name: null,
                startTime: null,
                endTime: null,
            }
        }),
        computed: {
            dateRangeText() {
                if (this.dates[0] === "" || this.dates[1] === "") {
                    return ""
                }
                return this.dates.join(" ~ ")
            }
        },
        components: {
            DefaultTemplate
        },
        methods: {
            async generateCouponType() {
                if (this._checkEmpty()) {
                    this.errorMessage = "빈칸이 있어요. 다시 확인해주세요."
                    return
                }
                this.errorMessage = null
                this.couponType.startTime = `${this.dates[0]}T00:00:00.000`
                this.couponType.endTime = `${this.dates[1]}T00:00:00.000`

                try {
                    const response = await couponTypeController.create(this.couponType)
                    console.log(response.data.couponType)
                    this.response.message = response.message
                    const {no, name, hasCode, discount, count, startTime, endTime} = response.data.couponType
                    this.response.no = no
                    this.response.name = name
                    this.response.hasCode = hasCode
                    this.response.discount = discount
                    this.response.count = count
                    this.response.startTime = startTime
                    this.response.endTime = endTime
                    this._initCouponType()
                    this.dialog = true
                } catch (e) {
                    this.errorMessage = e.response.data.message
                }
            },

            _initCouponType() {
                this.couponType.name = null
                this.couponType.count = null
                this.couponType.discount = null
                this.couponType.hasCode = false
                this.couponType.startTime = null
                this.couponType.endTime = null
            },

            _checkEmpty() {
                return (
                    !this.couponType.name ||
                    !this.couponType.count ||
                    !this.couponType.discount ||
                    !this.dates[0] ||
                    !this.dates[1]
                )
            },

            confirmBtnHandler() {
                this.$router.push('/coupontype')
            }
        }
    }
</script>

<style>
  .button-group-box {
    margin: 16px
  }
</style>

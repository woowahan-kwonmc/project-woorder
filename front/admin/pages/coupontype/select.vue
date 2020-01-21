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
            <div style="display: flex; flex-direction: row;">
              <div style="flex: 1;">
                {{couponType.no}}
              </div>
              <div style="flex: 4;">
                {{couponType.name}}
              </div>
              <div style="flex: 4">
                할인 금액 {{couponType.discount}} 원
              </div>
              <div style="flex: 2">
                {{couponType.hasCode ? '코드 쿠폰' : '다운로드 쿠폰'}}
              </div>
            </div>
          </v-expansion-panel-header>
          <v-expansion-panel-content>
            <div style="display: flex; flex-direction: row;">
              <div style="display: flex; flex-direction: column; flex: 10">
                <div style="display: flex; flex-direction: row; padding: 4px;">
                  <div style="flex: 1">No</div>
                  <div style="flex: 4">{{couponType.no}}</div>
                </div>
                <div style="display: flex; flex-direction: row; padding: 4px;">
                  <div style="flex: 1">쿠폰 타입 이름</div>
                  <div style="flex: 4">{{couponType.name}}</div>
                </div>
                <div style="display: flex; flex-direction: row; padding: 4px;">
                  <div style="flex: 1">할인 금액</div>
                  <div style="flex: 4">{{couponType.discount}} 원</div>
                </div>
                <div style="display: flex; flex-direction: row; padding: 4px;">
                  <div style="flex: 1">쿠폰 유형</div>
                  <div style="flex: 4">{{couponType.hasCode ? '코드 쿠폰' : '다운로드 쿠폰'}}</div>
                </div>
                <div style="display: flex; flex-direction: row; padding: 4px;">
                  <div style="flex: 1">총 발행 가능 쿠폰 수</div>
                  <div style="flex: 4">{{couponType.count}} 장</div>
                </div>
                <div style="display: flex; flex-direction: row; padding: 4px;">
                  <div style="flex: 1">사용 가능 기간</div>
                  <div style="flex: 4">
                    {{parseLocalTime(couponType.startTime)}} ~ {{parseLocalTime(couponType.endTime)}}
                  </div>
                </div>
              </div>
              <div style="flex: 1; flex-direction: column; justify-content: center; display: flex;">
                <div style="align-self: center">
                  <v-btn color="success" dark fab v-on:click="descBtnHandler(couponType.no)">목록</v-btn>
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
    import {couponTypeController} from "../../middleware/controller/CouponTypeController";

    export default {
        data: () => ({
            page: 1,
            num: 10,
            couponTypes: [],
            message: null,
        }),
        components: {
            WideTemplate
        },
        async beforeMount() {
            try {
                const response = await couponTypeController.select(this.page, 10);
                const {couponTypes} = response.data
                console.log(couponTypes)
                if (couponTypes.length === 0) {
                    this.message = "데이터가 없습니다.";
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
                    console.log(couponTypes)
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
                    console.log(couponTypes)
                    if (couponTypes.length === 0) {
                        return
                    }
                    this.couponTypes = couponTypes
                    this.page += 1
                } catch (e) {
                    console.log(e.response.data.message)
                }
            },

            async selectBtnClickHandler() {
                if (this.page == null) {
                    return
                }
                try {
                    const response = await couponTypeController.select(this.page, this.num);
                    const {couponTypes} = response.data
                    console.log(couponTypes)
                    if (couponTypes.length === 0) {
                        this.message = "데이터가 없습니다.";
                    }
                    this.couponTypes = couponTypes
                } catch (e) {
                    console.log(e.response.data.message)
                }
            },

            parseLocalTime(stringTime) {
                const date = new Date(stringTime);
                return `${date.getFullYear()}년 ${date.getMonth() + 1}월 ${date.getDate()}일`
            },

            descBtnHandler(id) {
                console.log(id)
            }
        }
    };
</script>

<style>
</style>


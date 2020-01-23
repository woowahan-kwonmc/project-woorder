<template>
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
      <div style="display: flex; flex-direction: row; padding: 4px;" v-if="couponSizeMode">
        <div style="flex: 1">현재 발행된 쿠폰 수</div>
        <div style="flex: 4">{{couponsSize}} 장</div>
      </div>
    </div>
  </div>
</template>

<script>
    export default {
        props: {
            couponType: {
                type: Object,
                required: true
            },
            couponsSize: {
                type: Number,
            },
            couponSizeMode: {
                type: Boolean,
            }
        },

        methods: {
            parseLocalTime(stringTime) {
                const date = new Date(stringTime);
                return `${date.getFullYear()}년 ${date.getMonth() + 1}월 ${date.getDate()}일`
            },
        }
    }
</script>

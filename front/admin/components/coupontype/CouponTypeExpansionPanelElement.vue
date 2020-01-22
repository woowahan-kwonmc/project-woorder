<template>
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
</template>

<script>
    export default {
        props: {
            couponType: {
                type: Object,
                required: true,
            }
        }
    }
</script>

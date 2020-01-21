<template>
  <default-template>
    <div class="index-buttons">
      <v-btn class="ma-2" color="indigo" dark tile to="/coupontype">쿠폰 타입</v-btn>
      <v-btn class="ma-2" color="indigo" dark tile to="/coupon">쿠폰</v-btn>
      <v-btn class="ma-2" color="indigo" dark tile to="/order">주문</v-btn>
      <v-btn class="ma-2" color="indigo" dark tile to="/payment">결제</v-btn>
    </div>
  </default-template>
</template>

<script>
    import DefaultTemplate from '../components/DefaultTemplate'

    export default {
        components: {
            DefaultTemplate,
        }
    }
</script>

<style scoped>
  .index-buttons {
    margin: 16px;
  }
</style>

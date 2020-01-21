<template>
  <default-template>
    <div>/coupontype</div>
    <div class="index-buttons">
      <v-btn class="ma-2" color="indigo" dark tile to="/coupontype/create">쿠폰 타입 생성</v-btn>
      <v-btn class="ma-2" color="indigo" dark tile to="/coupontype/select">쿠폰 타입 조회</v-btn>
    </div>
  </default-template>
</template>

<script>
    import DefaultTemplate from '../../components/DefaultTemplate'

    export default {
        components: {
            DefaultTemplate,
        }
    }
</script>

<style>
</style>


<template>
  <default-template>
    <div>/order</div>
    <div class="index-buttons">
      <v-btn class="ma-2" color="indigo" dark tile to="/order/select/all">주문 전체 조회</v-btn>
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


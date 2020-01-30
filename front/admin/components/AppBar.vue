<template>
  <v-app-bar color="deep-purple accent-4" dark fixed>
    <v-app-bar-nav-icon></v-app-bar-nav-icon>
    <v-toolbar-title>Woorder 어드민</v-toolbar-title>
    <v-spacer></v-spacer>
    <div class="index-buttons">
      <v-btn class="ma-2" dark tile to="/coupontype">쿠폰 타입</v-btn>
      <v-btn class="ma-2" dark tile to="/coupon">쿠폰</v-btn>
      <v-btn class="ma-2" dark tile to="/order">주문</v-btn>
    </div>
  </v-app-bar>
</template>

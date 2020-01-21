<template>
  <v-container>
    <v-app-bar color="deep-purple accent-4" dark fixed>
      <v-app-bar-nav-icon></v-app-bar-nav-icon>
      <v-toolbar-title>Woorder 어드민</v-toolbar-title>
      <v-spacer></v-spacer>
    </v-app-bar>
    <div class="body">
      <slot></slot>
    </div>
  </v-container>
</template>

<script>
    export default {
        methods: {}
    };
</script>

<style>
  .body {
    margin-top: 70px;
    overflow: scroll;
  }
</style>


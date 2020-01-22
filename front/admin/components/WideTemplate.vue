<template>
  <v-container>
    <app-bar></app-bar>
    <div class="body">
      <slot></slot>
    </div>
  </v-container>
</template>

<script>
    import AppBar from './AppBar'

    export default {
        methods: {},
        components: {
            AppBar,
        }
    };
</script>

<style>
  .body {
    margin-top: 70px;
    overflow: scroll;
  }
</style>


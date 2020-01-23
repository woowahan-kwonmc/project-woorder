<template>
  <section class="container">
    <app-bar></app-bar>
    <div class="body">
      <slot></slot>
    </div>
  </section>
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
  .container {
    min-height: 100vh;
    display: flex;
    justify-content: center;
    align-items: center;
    text-align: center;
  }

  .title {
    font-family: "Quicksand", "Source Sans Pro", -apple-system, BlinkMacSystemFont,
    "Segoe UI", Roboto, "Helvetica Neue", Arial, sans-serif; /* 1 */
    display: block;
    font-weight: 300;
    font-size: 100px;
    color: #35495e;
    letter-spacing: 1px;
  }

  .subtitle {
    font-weight: 300;
    font-size: 42px;
    color: #526488;
    word-spacing: 5px;
    padding-bottom: 15px;
  }

  .links {
    padding-top: 15px;
  }

  .body {
    margin-top: 70px;
    overflow: scroll;
  }
</style>


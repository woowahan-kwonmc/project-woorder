<template>
  <wide-template>
    <v-col>
      <v-toolbar>
        <v-toolbar-title>주문 조회</v-toolbar-title>
      </v-toolbar>
      <v-alert style="background-color: red" type="error" v-if="notFound">
        {{this.errorMessage}}
      </v-alert>
      <div>{{this.$route.params.id}}</div>
    </v-col>
  </wide-template>
</template>

<script>
    import WideTemplate from "../../../components/WideTemplate";
    import {orderController} from "../../../middleware/controller/OrderController";

    export default {
        data: () => ({
            notFound: false,
            errorMessage: null,
        }),

        components: {
            WideTemplate
        },

        async beforeMount() {
            try {
                const response = await orderController.findOrderByNo(this.$route.params.id)
                console.log(response.data)
            } catch (e) {
                if (e.response.data) {
                    this.errorMessage = e.response.data.message
                    this.notFound = true
                }
                console.log(e)
            }
        }
    }
</script>

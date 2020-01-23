<template>
  <wide-template>
    <v-col>
      <v-toolbar>
        <v-toolbar-title>전체 주문 조회</v-toolbar-title>
      </v-toolbar>
      <v-alert style="background-color: red" type="error" v-if="notFound">
        I'm an error alert.
      </v-alert>
    </v-col>
  </wide-template>
</template>

<script>
    import WideTemplate from "../../../../components/WideTemplate";
    import {OrderStatus} from "../../../../middleware/common/OrderStatus";
    import {orderController} from "../../../../middleware/controller/OrderController";

    export default {
        data: () => ({
            notFound: false,
            num: 10,
            page: 1,
            status: OrderStatus.ALL,
            orderedBy: 0,
        }),

        components: {
            WideTemplate
        },

        async beforeMount() {
            try {
                const response = await orderController.findConditionalOrders(this.page, this.num, this.status, this.orderedBy)
                console.log(response.data)
            } catch (e) {
                console.log(e)
            }
        }
    }
</script>

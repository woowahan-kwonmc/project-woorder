<template>
    <div>
        <v-col :key="i" style="display: flex; flex-direction: column;"
               v-for="(order, i) in payTarget.orders">
            <div style="display: flex; flex-direction: row; height: 20px;">
                <div style="display: flex; flex: 1;">주문 번호</div>
                <div style="display: flex; flex: 2;">{{order.no}}</div>
            </div>
            <div style="display: flex; flex-direction: row; height: 20px;">
                <div style="display: flex; flex: 1;">주문 상태</div>
                <div style="display: flex; flex: 2;">{{order.status}}</div>
            </div>
            <div style="display: flex; flex-direction: row; height: 20px;">
                <div style="display: flex; flex: 1;">주문 가격</div>
                <div style="display: flex; flex: 2;">{{order.price}}</div>
            </div>
            <div style="display: flex; flex-direction: row; height: 20px;">
                <div style="display: flex; flex: 1;">주문 수량</div>
                <div style="display: flex; flex: 2;">{{order.quantity}}</div>
            </div>
            <div style="display: flex; flex-direction: row; height: 20px;">
                <div style="display: flex; flex: 1;">주문 시간</div>
                <div style="display: flex; flex: 2;">{{order.createTime}}</div>
            </div>
        </v-col>
        <div style="display: flex; flex-direction: row; height: 20px; border-top: 1px dashed gray; padding: 8px; margin-bottom: 8px;">
            <div style="display: flex; flex: 1;">원래 가격</div>
            <div style="display: flex; flex: 2;">{{totalPrice(payTarget.orders)}}</div>
        </div>
    </div>
</template>

<script>
    export default {
        props: {
            payTarget: {
                type: Object,
                required: true,
            }
        },

        methods: {
            totalPrice(orders/*, coupons*/) {
                const price = orders
                    .map(order => order.price)
                    .reduce((a, b) => a + b, 0)
                return price
            }
        }
    }
</script>
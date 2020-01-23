<template>
    <div>
        <v-col :key="i" style="display: flex; flex-direction: column;"
               v-for="(item, i) in Array.from(order.basket.keys())">
            <div style="display: flex; flex-direction: row; height: 20px;">
                <div style="display: flex; flex: 1;">메뉴 이름</div>
                <div style="display: flex; flex: 2;">{{item.menuName}}</div>
            </div>
            <div style="display: flex; flex-direction: row; height: 20px;">
                <div style="display: flex; flex: 1;">메뉴 가격</div>
                <div style="display: flex; flex: 2;">{{item.menuPrice}}</div>
            </div>
            <div style="display: flex; flex-direction: row; height: 20px;">
                <div style="display: flex; flex: 1;">수량</div>
                <div style="display: flex; flex: 2;">{{order.basket.get(item)}}</div>
            </div>
        </v-col>
        <div style="display: flex; flex-direction: row; height: 20px; border-top: 1px dashed gray; padding: 8px; margin-bottom: 8px;">
            <div style="display: flex; flex: 1;">총 가격</div>
            <div style="display: flex; flex: 2;">{{totalPrice(this.order.basket)}}</div>
        </div>
    </div>
</template>

<script>
    export default {
        props: {
            order: {
                type: Object,
                required: true,
            }
        },

        methods: {
            totalPrice(orders) {
                return Array.from(orders)
                    .map(([key, value]) => key.menuPrice * value)
                    .reduce((a, b) => a + b, 0)
            },
        }
    }
</script>
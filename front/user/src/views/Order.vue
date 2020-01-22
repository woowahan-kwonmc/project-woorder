<template>
    <div>
        <need-login v-if="!this.$store.state.isLoggedIn"/>
        <v-col>
            <v-toolbar>
                <v-toolbar-title color="black" flat>주문 내역</v-toolbar-title>
            </v-toolbar>
            <div class="default-box-shadow">
                <div :key="i" style="padding: 8px; border-bottom: 1px solid #a6a6a6" v-for="(payment, i) in payments">
                    <div>결제 방법 - {{payment.method}}</div>
                    <div>결제 금액 - {{payment.status}}</div>
                    <div>결제 가격 - {{payment.price}}</div>
                    <div>결제 시간 - {{payment.createTime}}</div>
                </div>
            </div>
        </v-col>
    </div>
</template>

<script>
    import NeedLogin from "../components/NeedLogin";
    import {paymentController} from "../common/controller/PaymentController";

    export default {
        data: () => ({
            payments: [],
            page: 1,
            num: 10,
        }),

        components: {
            NeedLogin,
        },

        async beforeMount() {
            const memberNo = this.$store.state.member.memberNo
            if (!memberNo) {
                return
            }
            try {
                const response = await paymentController.selectPayments(memberNo, this.page, this.num)
                // eslint-disable-next-line no-console
                console.log(response)
                this.payments = response.data.payments
            } catch (e) {
                // eslint-disable-next-line no-console
                console.log(e.response)
            }
        }
    }
</script>

<style>
    .default-box-shadow {
        box-shadow: 0px 2px 4px -1px rgba(0, 0, 0, 0.2), 0px 4px 5px 0px rgba(0, 0, 0, 0.14), 0px 1px 10px 0px rgba(0, 0, 0, 0.12);
    }
</style>
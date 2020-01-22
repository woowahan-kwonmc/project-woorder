<template>
    <div>
        <need-login v-if="!this.$store.isLoggedIn"/>
        <!--  메뉴를 보여준다  -->
        <!--  메뉴를 선택한다  -->
        <!--  주문 정보를 생성한다.  -->
        <!--  결제 정보를 생성한다.  -->
        <!--  완료되었음을 보여준다.  -->
    </div>
</template>

<script>
    import NeedLogin from '../components/NeedLogin'

    export default {
        data: () => ({
            menus: []
        }),

        components: {
            NeedLogin
        }
    }

</script>
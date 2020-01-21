<template>
    <div id="global-wrapper">
        <v-app id="app">
            <v-app-bar
                    app
                    class="toolbar-bottom-navigation"
                    color="primary"
                    dark
            >
                <div class="d-flex align-center">우더(Woooooorder)</div>
            </v-app-bar>

            <div class="body">
                <v-content>
                    <HelloWorld/>
                    <HelloWorld/>
                    <HelloWorld/>
                    <HelloWorld/>
                    <HelloWorld/>
                </v-content>
            </div>

            <v-bottom-navigation
                    class="toolbar-bottom-navigation"
                    fixed
                    v-model="bottomNav"
            >
                <v-btn value="recent">
                    <span>Recent</span>
                    <v-icon>mdi-history</v-icon>
                </v-btn>

                <v-btn value="favorites">
                    <span>Favorites</span>
                    <v-icon>mdi-heart</v-icon>
                </v-btn>

                <v-btn value="nearby">
                    <span>Nearby</span>
                    <v-icon>mdi-map-marker</v-icon>
                </v-btn>
            </v-bottom-navigation>
        </v-app>
    </div>
</template>

<script>
    import HelloWorld from "./components/HelloWorld";

    export default {
        name: "App",

        components: {
            HelloWorld
        },

        data: () => ({
            bottomNav: "recent"
        })
    };
</script>

<style scoped>
    #global-wrapper {
        width: 100vw;
        height: 100vh;
        display: flex;
        flex-direction: column;
        align-items: center;
        position: relative;
        background-color: #ccc;
        overflow: hidden;
    }

    #app {
        background-color: "#ffffff";
        flex: 1;
        height: 100%;
        max-width: 500px;
        min-width: 250px;
        overflow: scroll;
        position: relative;
        width: 100%;
    }

    .toolbar-bottom-navigation {
        transform: translate(-50%, 0px) !important;
        left: 50% !important;
        width: 100%;
        max-width: 500px;
        min-width: 250px;
    }

    .body {
        margin-bottom: 56px;
    }
</style>
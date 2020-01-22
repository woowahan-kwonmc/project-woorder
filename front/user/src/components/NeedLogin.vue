<template>
    <div>
        <v-dialog max-width="300" persistent v-model="dialog">
            <v-card>
                <v-card-title class="headline">로그인이 필요합니다.</v-card-title>
                <v-card-text>
                    <v-container>
                        <v-row>
                            <v-col cols="12">
                                <v-text-field label="Member Name*" required v-model="memberName"></v-text-field>
                            </v-col>
                        </v-row>
                    </v-container>
                    <div style="text-align: center;" v-if="this.response.message">
                        {{ this.response.name }} - {{ this.response.message }}
                    </div>
                    <div style="color: red; text-align: center;" v-if="this.error.message">
                        {{ this.error.message }}
                    </div>
                </v-card-text>
                <v-card-actions>
                    <v-spacer></v-spacer>
                    <v-btn @click="createMember" color="green darken-1" text>회원가입</v-btn>
                    <v-btn @click="readMember" color="green darken-1" text>로그인</v-btn>
                </v-card-actions>
            </v-card>
        </v-dialog>
    </div>
</template>

<script>
    import {memberController} from "../common/controller/MemberController";

    export default {
        data: () => ({
            dialog: true,
            memberName: '',
            error: {
                message: null
            },
            response: {
                name: null,
                message: null
            }
        }),

        methods: {
            async createMember() {
                try {
                    const response = await memberController.createMember(this.memberName)
                    if (this.error.message) {
                        this.error.message = null
                    }
                    this.response.message = response.message
                    this.response.name = response.data.name
                } catch (e) {
                    if (e.response.data) {
                        this.error.message = e.response.data.message
                        return
                    }
                    this.error.message = "알 수 없는 에러 발생"
                }
            },

            async readMember() {
                try {
                    const response = await memberController.readMember(this.memberName)
                    const {member} = response.data
                    await this.$store.dispatch('setMember', member)
                    this.dialog = false
                } catch (e) {
                    if (e.response.data) {
                        this.error.message = e.response.data.message
                        return
                    }
                    this.error.message = "알 수 없는 에러 발생"
                }
            }
        }
    }
</script>
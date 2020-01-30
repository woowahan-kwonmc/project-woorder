import axios from 'axios'

const hostUrl = process.env.VUE_APP_SERVER_BASE_URL
    ? process.env.VUE_APP_SERVER_BASE_URL
    : 'http://localhost:8080/api/v1'

export default {
    base: () => (
        axios.create({
            baseURL: hostUrl
        })
    )
}

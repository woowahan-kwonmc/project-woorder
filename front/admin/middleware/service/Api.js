import axios from 'axios'

const hostUrl = 'http://localhost:8080/api/v1'

export default {
  base: () => (
    axios.create({
      baseURL: hostUrl
    })
  )
}

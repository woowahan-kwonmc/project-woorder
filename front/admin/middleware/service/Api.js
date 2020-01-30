import axios from 'axios'
import config from '../../api.config'

const hostUrl = config.baseUrl

export default {
  base: () => (
    axios.create({
      baseURL: hostUrl
    })
  )
}

module.exports = {
  ...require('./nuxt.config'),
  ...{
    build: {
      publicPath: '../../static/admin',
    },
  },
}

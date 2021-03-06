module.exports = {
  /*
  ** Headers of the page
  */
  head: {
    title: 'woorder-admin',
    meta: [
      {charset: 'utf-8'},
      {name: 'viewport', content: 'width=device-width, initial-scale=1'},
      {hid: 'description', name: 'description', content: 'woowahan pilot project woorder'}
    ],
    link: [
      {rel: 'icon', type: 'image/x-icon', href: '/favicon.ico'}
    ]
  },
  /*
  ** Customize the progress bar color
  */
  loading: {color: '#3B8070'},
  /*
  ** Modules
  */
  modules: [
    '@nuxtjs/vuetify',
  ],

  buildModules: [
    '@nuxtjs/dotenv',
  ],

  vuetify: {
    // Vuetify options
    //  theme: { }
  },
  /*
  ** Build configuration
  */
  build: {
    /*
    ** Run ESLint on save
    */
    extend(config, {isDev, isClient}) {

      config.node = {
        fs: 'empty'
      }

      if (isDev && isClient) {
        config.module.rules.push({
          enforce: 'pre',
          test: /\.(js|vue)$/,
          loader: 'eslint-loader',
          exclude: /(node_modules)/
        })
      }
    }
  },

  generate: {
    dir: '../../src/main/resources/templates/admin'
  }
}


module.exports = {
  lintOnSave: false,
  runtimeCompiler: true,
  devServer: {
    port: 3000,
    proxy: {
      'api':{
        target: 'http://localhost:8080',
        ws: true,
        changeOrigin: true
      }

    },

  },
  configureWebpack: {
    //Necessary to run npm link https://webpack.js.org/configuration/resolve/#resolve-symlinks
    resolve: {
       symlinks: false
    }
  },
  transpileDependencies: [
    '@coreui/utils',
    '@coreui/vue'
  ]
}

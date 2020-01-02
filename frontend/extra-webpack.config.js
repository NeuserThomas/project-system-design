const webpack = require('webpack');

module.exports = {
  plugins: [new webpack.DefinePlugin({
    'process.env': {
      HALL_PLANNING_SERVICE_PORT: JSON.stringify(process.env.HALL_PLANNING_SERVICE_PORT),
      HALL_PLANNING_SERVICE_HOST: JSON.stringify(process.env.HALL_PLANNING_SERVICE_HOST),
      SHOP_SERVICE_PORT: JSON.stringify(process.env.SHOP_SERVICE_PORT),
      SHOP_SERVICE_HOST: JSON.stringify(process.env.SHOP_SERVICE_HOST)

    }
  })]
}

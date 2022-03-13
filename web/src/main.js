import Vue from 'vue'
import request from '@/utils/request'
// ......

Vue.prototype.$post = request.post
Vue.prototype.$get = request.get
Vue.prototype.$put = request.put
Vue.prototype.$delete = request.delete
Vue.prototype.$login = request.login
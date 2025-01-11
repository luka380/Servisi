import { defineStore } from 'pinia'
import type { AuthInfo } from '@/types'
import { ref } from 'vue'

export const useAuthStore = defineStore('auth', () => {
  const authInfo = ref<AuthInfo | null>(getAuthInfoFromStorage())

  function login(newAuthInfo: AuthInfo) {
    storeAuthInfo(newAuthInfo)
    authInfo.value = newAuthInfo
  }

  function logout() {
    localStorage.removeItem('authInfo')
    authInfo.value = null
  }

  function isAuthenticated() {
    return !!authInfo.value
  }

  function getToken() {
    return !!authInfo.value ? authInfo.value.token : null
  }

  return {
    authInfo,
    login,
    logout,
    isAuthenticated,
    getToken
  }
})

function storeAuthInfo(authInfo: AuthInfo) {
  localStorage.setItem('authInfo', JSON.stringify(authInfo))
}

function getAuthInfoFromStorage(): AuthInfo | null {
  const jsonString = localStorage.getItem('authInfo')
  return jsonString ? (JSON.parse(jsonString) as AuthInfo) : null
}

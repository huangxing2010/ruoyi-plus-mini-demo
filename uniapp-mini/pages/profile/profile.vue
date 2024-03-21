<template>
	<view class="u-m-20">
		<u-navbar :is-back="true" title="个人信息" :border-bottom="false"></u-navbar>
		<view>
			<u-cell-group>
				<u-cell-item title="头像" :arrow="true" hover-class="none" @click="updateAvatar">
					<u-avatar :src="pic" size="100"></u-avatar>
				</u-cell-item>
			</u-cell-group>
			<u-cell-group>
				<u-cell-item title="昵称" :arrow="true" hover-class="none" @click="updateName">
					{{nickName}}
				</u-cell-item>
			</u-cell-group>
		</view>

		<u-modal v-model="showModel" @confirm="confirmName" ref="uModal" :async-close="true" title="设置昵称">
			<view class="slot-content">
				<u-input v-model="nickName" type="text" :border="false" placeholder="请输入昵称" />
			</view>
		</u-modal>



		<!-- 如果是微信登录小程序，则获取用户的昵称与头像 -->
		<!-- #ifdef MP-WEIXIN -->
		<!-- <u-button type="default">使用微信头像与昵称</u-button> -->
		<!-- #endif -->
	</view>
</template>

<script>
	import config from "@/common/config.js" // 全局配置文件
	export default {
		data() {
			return {
				// pic:uni.getStorageSync('lifeData').vuex_user.user.avatar.includes(config.staticUrl)?uni.getStorageSync('lifeData').vuex_user.user.avatar:config.staticUrl+uni.getStorageSync('lifeData').vuex_user.user.avatar,
				userId: null,
				nickName: null,
				showModel: false,
				pic: ''
			}
		},
		onLoad() {
			let lifeData = uni.getStorageSync('lifeData');
			this.nickName = lifeData.vuex_user.nickName;
			this.userId = lifeData.vuex_user.userId;
			this.pic = lifeData.vuex_user.avatar;
			// this.nickName = lifeData
		},
		methods: {
			updateName() {
				this.showModel = true;
			},
			confirmName() {
				if (!this.nickName) {
					// this.showModel = false;
					return this.$mytip.toast('请输入昵称')
				}else{
					let url = "api/updateProfile";
					// let vuex_user = uni.getStorageSync('lifeData').vuex_user
					this.$u.post(url, {
						userId: this.userId,
						nickName: this.nickName,
					}).then(data => {
						this.$mytip.toast('昵称修改成功，请重新登录')
						// 登录成功修改token与用户信息
						this.$u.vuex('vuex_token', '');
						this.$u.vuex('vuex_user', {});
						this.showModel = false;
						setTimeout(() => {
							return uni.reLaunch({
								url: '../login/login'
							})
						}, 1000)
					
					});
				}


				
			},
			updateAvatar() {
				this.$u.route('/pages/profile/avatar')

			},
			subProfile() {
				// 1.更新vuex中的用户信息
				this.$mytip.toast('修改成功')
				// 2.页面跳转
				uni.switchTab({
					url: '/pages/center/center'
				})
			},
			//根据token判断登录
			isLogin() {
				// 判断是否有token
				let lifeData = uni.getStorageSync('lifeData');
				let token = lifeData.vuex_token
				if (!token) {
					// 没有token 则跳转到登录
					return uni.reLaunch({
						url: '../login/login'
					})
				} else {
					// 判断Token是否有效
					let url = "/api/profile/isExpiration";
					this.$u.get(url, {
						token: token
					}).then(obj => {
						if (obj.data.start) {
							// 没有token过期则跳转到登录
							return uni.reLaunch({
								url: '../login/login'
							})
						}
					});
				}

			}

		}
	}
</script>

<style lang="scss" scoped>
	.slot-content {
		padding: 40rpx;
	}
</style>
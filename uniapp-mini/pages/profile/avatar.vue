<template>
	<view>
		<u-navbar :is-back="true" title="设置头像" :border-bottom="false"></u-navbar>
		<view class="wrap">
			<u-form-item label-width="150">
				<u-radio-group class="u-radio-group" v-model="radio" @change="radioGroupChange">
					<u-row gutter="16">
						<u-col span="4" v-for="(item, index) in radioList" :key="index" >
							<view class="col-mt">
							<u-radio shape="circle" :name="item.name" >
								<u-image width="120" height="120" :src="item.name"></u-image>
							</u-radio>
							</view>
						</u-col>
					</u-row>
				</u-radio-group>
			</u-form-item>

			<view class="u-m-t-20 btn">
				<u-button type="primary" @click="subProfile">保存</u-button>
			</view>
		</view>
	</view>
</template>

<script>
	import config from "@/common/config.js" // 全局配置文件
	export default {
		data() {
			return {
				// 服务器地址
				payType: '支付宝',
				radio: '',
				radioList: [{
						name: '/static/img/avatar/A0001.jpg',
					},
					{
						name: '/static/img/avatar/A0002.jpg'
					},
					{
						name: '/static/img/avatar/A0003.jpg'
					},
					{
						name: '/static/img/avatar/A0004.jpg'
					},
					{
						name: '/static/img/avatar/A0005.jpg'
					},
					{
						name: '/static/img/avatar/A0006.jpg'
					}
				],


			};
		},
		
		methods: {
			subProfile() {
				let user = uni.getStorageSync('lifeData').vuex_user
				if (!this.radio) {
					// this.showModel = false;
					return this.$mytip.toast('请选择您喜欢的头像！')
				} else {
					let url = "api/updateProfile";
					this.$u.post(url, {
						userId: user.userId,
						avatar: this.radio
					}).then(data => {
						this.$mytip.toast('保存成功，请重新登录')
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
			// radio选择发生变化
			radioGroupChange(e) {
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
	};
</script>

<style>
	page {
		background-color: #FFFFFF;
	}
</style>
<style scoped lang="scss">
	.wrap {
		padding: 30rpx;
	}

	.slot-btn {
		width: 690rpx;
		height: 690rpx;
		display: flex;
		justify-content: center;
		align-items: center;
		background: rgb(244, 245, 246);
		border-radius: 10rpx;
	}

	.slot-btn__hover {
		background-color: rgb(235, 236, 238);
	}

	.col-mt {
		height: 180rpx;
	}
</style>
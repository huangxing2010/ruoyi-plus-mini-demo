<template>
	<view class="wrap">
		<u-swiper :list="bannerList"></u-swiper>

		<view class="banner">
			<u-grid :col="3">
				<u-grid-item v-for="item in navbarList" @click="toLink(item.navUrl)">
					<u-icon :name="item.navIcon" :size="80"></u-icon>
					<view class="grid-text">{{item.navName}}</view>
				</u-grid-item>
			</u-grid>
		</view>

		<u-notice-bar mode="horizontal" :list="list" speed="80" bg-color="#dbf1e1"></u-notice-bar>

		<u-card margin="12rpx">
			<view class="" slot="head">
				<u-section title="今日热门" @click="toArticle" sub-title="查看更多"></u-section>
			</view>
			<view class="" slot="body">
				<view v-for="vo in articleList" @click="toConternt(vo.articleId)"
					class="u-body-item u-flex u-border-bottom u-col-between u-p-t-0">
					<view class="u-body-item-title u-line-2">{{vo.articleTitle}}</view>
					<image :src="vo.thumbnail == '' ? thumbnail : vo.thumbnail " mode="aspectFill"></image>
				</view>
			</view>

		</u-card>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				navbarList: [],
				bannerList: [{
					image: '/static/img/index/swiper/swiper2.png',
					title: '01'
				},{
					image: '/static/img/index/swiper/swiper.jpg',
					title: '01'
				}],
				articleList: [],
				thumbnail: '/static/img/thumbnail.jpg',
				list: [
					'寒雨连江夜入吴',
					'平明送客楚山孤',
					'洛阳亲友如相问',
					'一片冰心在玉壶'
				]
			}
		},
		onReady() {
			this.isLogin()
			this.getNavbarList()
			this.getBannerList()
			this.getArticleList()
		},
		methods: {
			//获取导航数据
			getNavbarList() {
				let url = "/api/miniWechat/getNav";
				this.$u.get(url).then(res => {
					this.navbarList = res.data;
				})
			},
			//获取幻灯数据
			getBannerList() {
				let url = "/api/miniWechat/getBanner";
				this.$u.get(url).then(res => {

					let banner = []
					let obj = res.data;
					obj.forEach((item, index) => {
						banner.push({
							image: item.thumbnail,
							title: item.bannerName
						})
					})
					//无oss资源，暂时不调用图片数据
					// this.bannerList = banner;
				})
			},
			//获取文章列表
			getArticleList() {
				let url = "/api/miniWechat/getArticleList";
				let data = {
					artType: '1'
				}
				this.$u.get(url, data).then(res => {
					this.articleList = res.data
				})
			},
			//导航点击跳转
			toLink(url) {
				// 无参数
				this.$u.route(url);
			},
			//更多｜跳转文章列表页
			toArticle() {
				this.$u.route("pages/article/list");
			},
			//跳转文章内容页
			toConternt(aid) {
				this.$u.route({
					url: "pages/article/content",
					params: {
						articleId: aid
					}

				});
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
	.wrap {}

	.grid-text {
		font-size: 28rpx;
		margin-top: 4rpx;
		color: $u-type-info;
	}

	.banner {
		margin: 12rpx;
	}

	// 卡片样式
	.u-card-wrap {
		background-color: $u-bg-color;
		padding: 1px;
	}

	.u-body-item {
		font-size: 32rpx;
		color: #333;
		padding: 20rpx 10rpx;
	}

	.u-body-item image {
		width: 120rpx;
		flex: 0 0 120rpx;
		height: 120rpx;
		// border-radius: 8rpx;
		margin-left: 12rpx;
	}
</style>
<template>
	<view class="u-content">
		<h4 class="cn">{{articleInfo.articleTitle}}</h4>
		<h5 class="cn">
			作者：{{articleInfo.createBy}}<span>|</span>来源：{{articleInfo.source}}<span>|</span>访问{{articleInfo.artHits}}次数
		</h5>
		<u-divider bg-color="#f5f5f5"></u-divider>
		<u-parse :html="articleInfo.content" :tag-style="style"></u-parse>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				articleInfo: '',
				content: '',
				style: {
					// 字符串的形式
					p: 'color:#2b2b2b;font-size:28rpx;text-indent: 2em;line-height: 48rpx;padding: 12rpx 0;',
					span: 'font-size: 30rpx'
				}
			}
		},
		onLoad(option) {
			this.getArticleContent(option.articleId)
		},
		methods: {
			getArticleContent(aid) {
				let url = "/api/miniWechat/getArticleContent?articleId=" + aid;
				this.$u.get(url).then(res => {
					this.articleInfo = res.data;
				})

			}
		}
	}
</script>

<style lang="scss" scoped>
	/* App.vue 文件 */
	page {
		background-color: #f0f0f0;
		/* 你想要的背景颜色 */
	}

	.u-content {
		// margin-top: 100rpx;
		padding: 24rpx;

	}

	.cn {
		text-align: center;
	}

	h4 {
		font-size: 36rpx;
	}

	h5 {
		font-size: 24rpx;
		color: #999999;
		margin: 24rpx 0;
	}

	h5 span {
		padding: 0 24rpx;
	}

	interlayer {
		
		
	}
</style>
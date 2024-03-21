<template>
	<view class="wrap">
		<view class="item u-border-bottom" v-for="(item, index) in articleList" :key="index" @click="toConternt(item.articleId)">
			<span>{{$u.timeFormat(item.createTime, 'yyyy-mm-dd')}}</span>{{item.articleTitle}}
		</view>
		<u-loadmore :status="status" />
	</view>
</template>

<script>
	export default {
		data() {
			return {
				status: 'loadmore',
				//一次加载总数
				pageSize : 15,
				pageNum: 1,
				total: 1,
				articleList: []
			}
		},
		onReachBottom() {
			this.status = 'loading';
			this.pageNum = this.pageNum + 1;
			let data = {
				pageNum: this.pageNum,
				pageSize: this.pageSize,
			}
			setTimeout(() => {
				// this.list += 15;
				// this.list = 5;
				if (this.pageNum >= this.total + 1) {
					this.status = 'nomore';
				} else {
					this.status = 'loading';
					this.getArticleList(data)
				}
			}, 1000)
		},
		methods: {
			//获取导航数据
			getArticleList(data) {
				let url = "/api/miniWechat/articlePageList";
				this.$u.get(url, data).then(res => {
					if(this.articleList.length == 0){
						this.articleList = res.rows
					}else{
						this.articleList = [...this.articleList, ...res.rows]
					}
					this.total = Math.floor(res.total / this.pageSize) + 1;
				})
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
		},
		onLoad() {
			this.getArticleList({pageNum: this.pageNum, pageSize: this.pageSize,})
		}
	}
</script>

<style lang="scss" scoped>
	.wrap {
		padding: 24rpx;
	}

	.item {
		padding: 24rpx 0;
		color: $u-content-color;
		font-size: 28rpx;
	}
	.item span {float: right;color: #ccc;}
</style>
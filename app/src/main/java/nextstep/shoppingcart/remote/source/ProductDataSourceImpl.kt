package nextstep.shoppingcart.remote.source

import nextstep.shoppingcart.data.source.ProductDataSource
import nextstep.shoppingcart.domain.model.Product
import javax.inject.Inject

class ProductDataSourceImpl @Inject constructor() : ProductDataSource {
    override fun getProducts(): Result<List<Product>> {
        return try {
            Result.success(products)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override fun getProductById(productId: Long): Result<Product?> {
        return try {
            Result.success(products.find { it.id == productId })
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    companion object {
        val products: List<Product> =
            listOf(
                Product(
                    id = 0L,
                    imageUrl =
                    "https://search.pstatic.net/common/?src=http%3A%2F%2Fblogfiles.naver.net" +
                            "%2FMjAyNDAyMjNfMjkg%2FMDAxNzA4NjE1NTg1ODg5.ZFPHZ3Q2HzH7GcYA1_Jl0lsIdvAnzUF2h6Qd6bgDLHkg." +
                            "_7ffkgE45HXRVgX2Bywc3B320_tuatBww5y1hS4xjWQg.JPEG%2FIMG_5278.jpg&type=sc960_832",
                    name = "대전 장인약과",
                    price = 12000,
                ),
                Product(
                    id = 1L,
                    imageUrl =
                    "https://search.pstatic.net/common/?src=http%3A%2F%2Fblogfiles.naver.net" +
                            "%2FMjAyNDAxMDVfNjYg%2FMDAxNzA0NDU0MTYwMTAx.4pxrLnIdvFp8KDGAnGkbl8zHo5Mcn0d-yD7pzToeiSsg." +
                            "lF4rd6908c0j_7kfxBr_u4MSdjq73RkhzKfRk7Z6VUMg.JPEG.rbxod123%2F1704454160034.jpg&type=sc960_832",
                    name = "라라스윗 바닐라",
                    price = 15000,
                ),
                Product(
                    id = 2L,
                    imageUrl =
                    "https://search.pstatic.net/common/?src=http%3A%2F%2Fshop1.phinf.naver.net" +
                            "%2F20220419_195%2F1650332783362X5wqH_JPEG%2F51468679090044420_1417181296.jpg&type=sc960_832",
                    name = "청포도",
                    price = 8000,
                ),
                Product(
                    id = 3L,
                    imageUrl =
                    "https://search.pstatic.net/common/?src=http%3A%2F%2Fblogfiles.naver.net" +
                            "%2FMjAyNDA0MjZfMTU4%2FMDAxNzE0MTI0NzA4ODY5.oeDm3aXfYKCwJBx6W5pvgeGbEnv9Pl9M7-KS8RSdESgg." +
                            "---7jVzxMg2CyWfdaymPSlVDOf-VtgmZiU4bThZNDEsg.JPEG%2F3.jpg&type=sc960_832",
                    name = "유기농 쑥",
                    price = 9500,
                ),
                Product(
                    id = 4L,
                    imageUrl =
                    "https://search.pstatic.net/common/?src=http%3A%2F%2Fblogfiles.naver.net" +
                            "%2FMjAyNDA0MjhfMjA0%2FMDAxNzE0MjMyNDEwMjUy.oPmks3C6DLu9bbeU1ZAAbQsuDNU0STlZmESokn_L32Ig." +
                            "qNELX5_3ojNWAuh9ggXIx_YuLe7N9wqJDp6CsxwKp-0g.JPEG%2Fart_1455355748.jpg&type=sc960_832",
                    name = "대저 토마토",
                    price = 11000,
                ),
                Product(
                    id = 5L,
                    imageUrl =
                    "https://search.pstatic.net/common/?src=http%3A%2F%2Fimgnews.naver.net" +
                            "%2Fimage%2F277%2F2013%2F11%2F10%2F2013111012173878553_1_59_20131110122101.jpg&type=sc960_832",
                    name = "논산 설향 딸기",
                    price = 13000,
                ),
                Product(
                    id = 6L,
                    imageUrl =
                    "https://search.pstatic.net/common/?src=http%3A%2F%2Fblogfiles.naver.net" +
                            "%2FMjAyMzEwMjlfNCAg%2FMDAxNjk4NTY4MDU4MzI1.jbHZDZihchAU2omlt5kKT2Y-sIMLAeFmK-N124BfwSkg." +
                            "55nLXObBCS97UUPyExqsV1HEa_2HY6kS4MSlg0JJ12Eg.JPEG.sisia81%2FCK_pc0030942615.jpg&type=sc960_832",
                    name = "올리브",
                    price = 20000,
                ),
                Product(
                    id = 7L,
                    imageUrl =
                    "https://search.pstatic.net/common/?src=http%3A%2F%2Fshop1.phinf.naver.net" +
                            "%2F20240404_106%2F1712192530083b4K6O_JPEG%2F62268170973943746_107419204.jpg&type=sc960_832",
                    name = "녹두고물 꿀설기",
                    price = 16000,
                ),
                Product(
                    id = 8L,
                    imageUrl =
                    "https://search.pstatic.net/common/?src=http%3A%2F%2Fblogfiles.naver.net" +
                            "%2FMjAyNDA0MTZfNzIg%2FMDAxNzEzMjQ1NzAxNTk3.J_U-og2VONyRLnh8GEOredJBlKa4G92kXJgRONPugHAg." +
                            "3focus3kJUN7PMwiGQcRwfhhC9_uzMYlMd9DqNkvdtog.JPEG%2FNSC20240414%25A3%25DF223625." +
                            "jpg&type=sc960_832",
                    name = "밀크 클래식 쌀과자",
                    price = 9000,
                ),
                Product(
                    id = 9L,
                    imageUrl =
                    "https://search.pstatic.net/common/?src=http%3A%2F%2Fblogfiles.naver.net" +
                            "%2FMjAyNDA0MTRfMTI5%2FMDAxNzEzMDc2NjQ5Mzg2.-9IFzuLjUDDH7Tzx3Vjqvqe_mYRgjnfiCMSMV6NzI4Eg." +
                            "krg2ZvGecdmZdz4xF4-7dUNveZOjkrUxRq8ag3qRPrcg.PNG" +
                            "%2F%25BD%25BA%25C5%25A9%25B8%25B0%25BC%25A6_2024-04-14_152851.png&type=sc960_832",
                    name = "양배추",
                    price = 7000,
                ),
                Product(
                    id = 10L,
                    imageUrl =
                    "https://search.pstatic.net/common/?src=http%3A%2F%2Fblogfiles.naver.net" +
                            "%2FMjAyNDA0MjRfNjkg%2FMDAxNzEzOTI0OTY0MTAw.Z3BrwiQtHcm0wqcytLsgAGp9BL5FVkhpBn5QXLXugn4g." +
                            "79JGr9GD9AHbgKHi1WvCyRhmELbOSJ5EMOmRGJhYaecg.JPEG%2F10.jpg&type=sc960_832",
                    name = "당근",
                    price = 12000,
                ),
                Product(
                    id = 11L,
                    imageUrl =
                    "https://search.pstatic.net/sunny/?src=https%3A%2F%2Fimg1.tmon.kr" +
                            "%2Fcdn4%2Fdeals%2F2024%2F02%2F28%2F25807373110%2Ffront_c9653_skjvr.jpg&type=sc960_832",
                    name = "하겐다즈 초콜릿 (쿼터) 946ml",
                    price = 18000,
                ),
            )
    }
}



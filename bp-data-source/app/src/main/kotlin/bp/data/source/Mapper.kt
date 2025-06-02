package bp.data.source

interface Mapper<A, B> {
    fun map(input: A, outputClass: Class<B>): B
}
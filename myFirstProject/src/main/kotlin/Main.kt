fun main() {
    println("Hello World!")
    val recipe = Recipe()
    recipe.addIngredient("Rice")
    recipe.addIngredient("Chicken")
    println(recipe.getIngredients())
}
class Recipe {

    private val ingredients = mutableListOf<String>()

    fun addIngredient (name: String) {
        ingredients.add(name)
    }
    fun getIngredients() : List<String>{
        return this.ingredients
    }
}
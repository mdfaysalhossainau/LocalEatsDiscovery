package com.example.localeatsdiscovery

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.BookmarkBorder
import androidx.compose.material.icons.filled.Business
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Facebook
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Logout
import androidx.compose.material.icons.filled.Map
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.PersonAdd
import androidx.compose.material.icons.filled.Restaurant
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.Storefront
import androidx.compose.material.icons.filled.Web
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.res.painterResource
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import kotlinx.coroutines.launch
import com.example.localeatsdiscovery.login.LoginActivity
import com.example.localeatsdiscovery.auth.SessionManager

// ============================================================
// COLOURS
// ============================================================

val Purple = Color(0xFF7446D9)
val LightPurple = Color(0xFFF0E9FF)
val VeryLightPurple = Color(0xFFF8F5FF)
val DarkText = Color(0xFF272238)
val GreyText = Color(0xFF77727F)
val White = Color.White
val Yellow = Color(0xFFFFC94A)
val Green = Color(0xFF2E8B57)

// ============================================================
// MEAL MODEL
// ============================================================

data class Meal(
    val name: String,
    val restaurant: String,
    val location: String,
    val cuisine: String,
    val price: Double,
    val rating: Double,
    val image: Int
)

// ============================================================
// RESTAURANT MODEL
// ============================================================

data class RestaurantInfo(
    val name: String,
    val cuisine: String,
    val address: String,
    val phone: String,
    val hours: String,
    val description: String
)

// ============================================================
// MEAL DATA
// ============================================================

val mealList = listOf(

    Meal(
        "Chicken Rice",
        "Hainan Chicken House",
        "Southport",
        "Asian",
        11.90,
        4.6,
        R.drawable.hainan_chicken_rice
    ),

    Meal(
        "Classic Beef Burger",
        "Classic Burger House",
        "Southport",
        "Fast Food",
        10.90,
        4.5,
        R.drawable.classic_beef_burger
    ),

    Meal(
        "Chicken Burger",
        "Coast Burger Co.",
        "Southport",
        "Fast Food",
        12.90,
        4.5,
        R.drawable.coast_burger_co_cover
    ),

    Meal(
        "Curry Laksa",
        "Bay Street Eats",
        "Southport",
        "Asian",
        13.90,
        4.4,
        R.drawable.curry_laksa
    ),

    Meal(
        "Fish & Chips",
        "Bay Street Eats",
        "Southport",
        "Seafood",
        14.90,
        4.3,
        R.drawable.fish_chips
    ),

    Meal(
        "Ramen Special",
        "Ramen House",
        "Southport",
        "Japanese",
        15.90,
        4.5,
        R.drawable.ramen_house_special
    ),

    Meal(
        "Seafood Laksa",
        "Bay Street Eats",
        "Southport",
        "Seafood",
        16.90,
        4.4,
        R.drawable.seafood_laksa
    ),

    Meal(
        "Teriyaki Chicken Bowl",
        "Coast Eats",
        "Southport",
        "Asian",
        14.50,
        4.5,
        R.drawable.teriyaki_chicken_bowl
    ),

    Meal(
        "Tonkotsu Ramen",
        "Ramen House",
        "Southport",
        "Japanese",
        16.90,
        4.6,
        R.drawable.tonkotsu_ramen
    ),

    Meal(
        "Vietnamese Beef Pho",
        "Pho Corner",
        "Southport",
        "Vietnamese",
        15.90,
        4.5,
        R.drawable.vietnamese_beef_pho
    )
)

// ============================================================
// RESTAURANT DATA
// ============================================================

val restaurantList = listOf(

    RestaurantInfo(
        "Hainan Chicken House",
        "Asian",
        "Southport, Gold Coast, Queensland",
        "07 5555 1001",
        "11:00 AM – 9:00 PM",
        "Affordable Asian meals with a focus on Hainan chicken rice and simple local favourites."
    ),

    RestaurantInfo(
        "Classic Burger House",
        "Fast Food",
        "Southport, Gold Coast, Queensland",
        "07 5555 1002",
        "11:00 AM – 10:00 PM",
        "Affordable classic burgers, chips and quick meals for local customers."
    ),

    RestaurantInfo(
        "Coast Burger Co.",
        "Fast Food",
        "Southport, Gold Coast, Queensland",
        "07 5555 1003",
        "11:00 AM – 9:30 PM",
        "A local burger restaurant offering affordable burgers and casual meals."
    ),

    RestaurantInfo(
        "Bay Street Eats",
        "Asian / Seafood",
        "Southport, Gold Coast, Queensland",
        "07 5555 1004",
        "11:00 AM – 9:00 PM",
        "A casual local restaurant offering laksa, seafood and affordable Asian meals."
    ),

    RestaurantInfo(
        "Ramen House",
        "Japanese",
        "Southport, Gold Coast, Queensland",
        "07 5555 1005",
        "12:00 PM – 9:30 PM",
        "Japanese ramen restaurant offering affordable ramen options and casual dining."
    ),

    RestaurantInfo(
        "Pho Corner",
        "Vietnamese",
        "Southport, Gold Coast, Queensland",
        "07 5555 1006",
        "11:00 AM – 9:00 PM",
        "Vietnamese restaurant offering pho and other affordable local favourites."
    ),

    RestaurantInfo(
        "Coast Eats",
        "Asian",
        "Southport, Gold Coast, Queensland",
        "07 5555 1007",
        "11:00 AM – 9:00 PM",
        "Affordable Asian meals designed for local customers."
    )
)

// ============================================================
// MAIN ACTIVITY
// ============================================================

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Always check the login session before opening the Home screen.
        // This also protects the app if MainActivity is launched directly.
        if (!SessionManager.isLoggedIn(this)) {
            val intent = Intent(
                this,
                LoginActivity::class.java
            ).apply {
                flags =
                    Intent.FLAG_ACTIVITY_NEW_TASK or
                            Intent.FLAG_ACTIVITY_CLEAR_TASK
            }

            startActivity(intent)
            finish()
            return
        }

        setContent {
            LocalEatsApp()
        }
    }
}

// ============================================================
// MAIN APP
// ============================================================

@Composable
fun LocalEatsApp() {

    var screen by remember {
        mutableStateOf("discover")
    }

    var selectedMeal by remember {
        mutableStateOf<Meal?>(null)
    }

    var selectedRestaurant by remember {
        mutableStateOf<RestaurantInfo?>(null)
    }

    var invoiceNumber by remember {
        mutableStateOf("")
    }

    var paymentDate by remember {
        mutableStateOf("")
    }

    val drawerState = rememberDrawerState(
        initialValue = DrawerValue.Closed
    )

    val scope = rememberCoroutineScope()

    val context = LocalContext.current

    fun closeDrawer() {
        scope.launch {
            drawerState.close()
        }
    }

    ModalNavigationDrawer(

        drawerState = drawerState,

        drawerContent = {

            ModalDrawerSheet(
                drawerContainerColor = White
            ) {

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(rememberScrollState())
                        .navigationBarsPadding()
                ) {

                    Spacer(
                        modifier = Modifier.height(24.dp)
                    )

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(
                                horizontal = 24.dp
                            ),
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        Image(
                            painter = painterResource(
                                id = R.drawable.local_eats_logo
                            ),
                            contentDescription = "Local Eats Logo",
                            modifier = Modifier
                                .size(44.dp)
                                .clip(
                                    RoundedCornerShape(10.dp)
                                ),
                            contentScale = ContentScale.Crop
                        )

                        Spacer(
                            modifier = Modifier.width(12.dp)
                        )

                        Column {

                            Text(
                                text = "Local Eats",
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold,
                                color = DarkText
                            )

                            Text(
                                text = "Discovery",
                                fontSize = 12.sp,
                                color = Purple
                            )
                        }
                    }

                    Spacer(
                        modifier = Modifier.height(28.dp)
                    )

                    DrawerSectionTitle(
                        title = "DISCOVER"
                    )

                    DrawerItem(
                        icon = Icons.Default.Home,
                        title = "Discover",
                        selected = screen == "discover",
                        onClick = {
                            screen = "discover"
                            closeDrawer()
                        }
                    )

                    DrawerItem(
                        icon = Icons.Default.Restaurant,
                        title = "Meal Match",
                        selected = screen == "meal_match",
                        onClick = {
                            screen = "meal_match"
                            closeDrawer()
                        }
                    )

                    DrawerItem(
                        icon = Icons.Default.Star,
                        title = "Deals",
                        selected = screen == "deals",
                        onClick = {
                            screen = "deals"
                            closeDrawer()
                        }
                    )

                    Spacer(
                        modifier = Modifier.height(14.dp)
                    )

                    DrawerSectionTitle(
                        title = "BUSINESS"
                    )

                    DrawerItem(
                        icon = Icons.Default.Storefront,
                        title = "For Restaurants",
                        selected = screen == "restaurants",
                        onClick = {
                            screen = "restaurants"
                            closeDrawer()
                        }
                    )

                    DrawerItem(
                        icon = Icons.Default.PersonAdd,
                        title = "Partnership Enquiry",
                        selected = screen == "enquiry",
                        onClick = {
                            screen = "enquiry"
                            closeDrawer()
                        }
                    )

                    Spacer(
                        modifier = Modifier.height(14.dp)
                    )

                    DrawerSectionTitle(
                        title = "INFORMATION"
                    )

                    DrawerItem(
                        icon = Icons.Default.Info,
                        title = "About",
                        selected = screen == "about",
                        onClick = {
                            screen = "about"
                            closeDrawer()
                        }
                    )

                    DrawerItem(
                        icon = Icons.Default.Email,
                        title = "Contact",
                        selected = screen == "contact",
                        onClick = {
                            screen = "contact"
                            closeDrawer()
                        }
                    )

                    DrawerItem(
                        icon = Icons.Default.BookmarkBorder,
                        title = "Saved Meals",
                        selected = screen == "saved",
                        onClick = {
                            screen = "saved"
                            closeDrawer()
                        }
                    )

                    Spacer(
                        modifier = Modifier.height(14.dp)
                    )

                    DrawerSectionTitle(
                        title = "ACCOUNT"
                    )

                    DrawerItem(
                        icon = Icons.Default.Settings,
                        title = "Settings",
                        selected = screen == "settings",
                        onClick = {
                            screen = "settings"
                            closeDrawer()
                        }
                    )

                    DrawerItem(
                        icon = Icons.Default.Logout,
                        title = "Logout",
                        selected = false,
                        onClick = {

                            SessionManager.setLoggedIn(
                                context,
                                false
                            )

                            val intent = Intent(
                                context,
                                LoginActivity::class.java
                            ).apply {
                                flags =
                                    Intent.FLAG_ACTIVITY_NEW_TASK or
                                            Intent.FLAG_ACTIVITY_CLEAR_TASK
                            }

                            context.startActivity(intent)
                        }
                    )

                    Spacer(
                        modifier = Modifier.height(24.dp)
                    )

                    Text(
                        text = "Local Eats Discovery",
                        fontSize = 12.sp,
                        color = GreyText,
                        modifier = Modifier.padding(
                            start = 24.dp,
                            bottom = 24.dp
                        )
                    )

                    Spacer(
                        modifier = Modifier.height(24.dp)
                    )
                }
            }
        }
    ) {

        Scaffold(

            topBar = {

                LocalEatsTopBar(
                    onMenuClick = {
                        scope.launch {
                            drawerState.open()
                        }
                    },

                    onSearchClick = {
                        screen = "discover"
                    },

                    onSavedClick = {
                        screen = "saved"
                    }
                )
            }

        ) { paddingValues ->

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .background(White)
            ) {

                when (screen) {

                    "discover" -> {

                        DiscoverScreen(
                            onMealClick = {
                                selectedMeal = it
                                screen = "details"
                            }
                        )
                    }

                    "details" -> {

                        selectedMeal?.let {

                            MealDetailsScreen(
                                meal = it,

                                onBack = {
                                    screen = "discover"
                                },

                                onRestaurant = {

                                    selectedRestaurant =
                                        restaurantList.find { restaurant ->
                                            restaurant.name ==
                                                    it.restaurant
                                        }

                                    screen = "restaurant"
                                }
                            )
                        }
                    }

                    "restaurant" -> {

                        selectedRestaurant?.let {

                            RestaurantDetailsScreen(
                                restaurant = it,

                                onBack = {
                                    screen = "details"
                                },

                                onMealClick = { meal ->
                                    selectedMeal = meal
                                    screen = "details"
                                }
                            )
                        }
                    }

                    "meal_match" -> {

                        MealMatchScreen(
                            onBack = {
                                screen = "discover"
                            },

                            onMealClick = {
                                selectedMeal = it
                                screen = "details"
                            }
                        )
                    }

                    "deals" -> {

                        DealsScreen(
                            onBack = {
                                screen = "discover"
                            },

                            onMealClick = {
                                selectedMeal = it
                                screen = "details"
                            }
                        )
                    }

                    "restaurants" -> {

                        RestaurantsScreen(
                            onBack = {
                                screen = "discover"
                            },

                            onRestaurantClick = {
                                selectedRestaurant = it
                                screen = "restaurant"
                            },

                            onEnquiryClick = {
                                screen = "enquiry"
                            },

                            onPromotionClick = {
                                screen = "promotion"
                            }
                        )
                    }

                    "about" -> {

                        AboutScreen(
                            onBack = {
                                screen = "discover"
                            }
                        )
                    }

                    "contact" -> {

                        ContactScreen(
                            onBack = {
                                screen = "discover"
                            },

                            onEnquiryClick = {
                                screen = "enquiry"
                            },

                            onSendMessageClick = {
                                screen = "message"
                            }
                        )
                    }

                    "enquiry" -> {

                        RestaurantEnquiryScreen(
                            onBack = {
                                screen = "contact"
                            },

                            onSubmitted = {
                                screen = "confirmation"
                            }
                        )
                    }

                    "confirmation" -> {

                        EnquiryConfirmationScreen(
                            onContinue = {
                                screen = "restaurants"
                            }
                        )
                    }

                    "promotion" -> {

                        PromotionScreen(
                            onBack = {
                                screen = "restaurants"
                            },

                            onPaymentClick = {
                                screen = "payment"
                            }
                        )
                    }

                    "payment" -> {

                        DemoPaymentScreen(
                            onBack = {
                                screen = "promotion"
                            },

                            onPaymentSuccess = {

                                invoiceNumber =
                                    "LED-${System.currentTimeMillis().toString().takeLast(6)}"

                                paymentDate =
                                    SimpleDateFormat(
                                        "dd MMM yyyy, hh:mm a",
                                        Locale.getDefault()
                                    ).format(Date())

                                screen = "invoice"
                            }
                        )
                    }

                    "invoice" -> {

                        InvoiceScreen(
                            invoiceNumber = invoiceNumber,
                            paymentDate = paymentDate,

                            onBack = {
                                screen = "discover"
                            }
                        )
                    }

                    "saved" -> {

                        SavedMealsScreen(
                            onBack = {
                                screen = "discover"
                            },

                            onMealClick = {
                                selectedMeal = it
                                screen = "details"
                            }
                        )
                    }

                    "settings" -> {

                        SettingsScreen(
                            onBack = {
                                screen = "discover"
                            }
                        )
                    }

                    "message" -> {

                        SendMessageScreen(
                            onBack = {
                                screen = "contact"
                            },

                            onSent = {
                                screen = "contact"
                            }
                        )
                    }
                }
            }
        }
    }
}

// ============================================================
// TOP BAR
// ============================================================

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LocalEatsTopBar(
    onMenuClick: () -> Unit,
    onSearchClick: () -> Unit,
    onSavedClick: () -> Unit
) {

    TopAppBar(

        // FIX:
        // Move the complete top bar slightly down
        // so the menu button does not sit too close
        // to the status bar.
        modifier = Modifier.padding(
            top = 8.dp
        ),

        navigationIcon = {

            IconButton(
                onClick = onMenuClick
            ) {

                Icon(
                    imageVector = Icons.Default.Menu,
                    contentDescription = "Menu",
                    tint = DarkText
                )
            }
        },

        title = {

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {

                Image(
                    painter = painterResource(
                        id = R.drawable.local_eats_logo
                    ),
                    contentDescription = "Local Eats Logo",
                    modifier = Modifier
                        .size(36.dp)
                        .clip(
                            RoundedCornerShape(9.dp)
                        ),
                    contentScale = ContentScale.Crop
                )

                Spacer(
                    modifier = Modifier.width(8.dp)
                )

                Column {

                    Text(
                        text = "Local Eats",
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold,
                        color = DarkText
                    )

                    Text(
                        text = "Discovery",
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Bold,
                        color = DarkText
                    )
                }
            }
        },

        actions = {

            IconButton(
                onClick = onSearchClick
            ) {

                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "Search",
                    tint = DarkText
                )
            }

            IconButton(
                onClick = onSavedClick
            ) {

                Icon(
                    imageVector = Icons.Default.BookmarkBorder,
                    contentDescription = "Saved meals",
                    tint = DarkText
                )
            }
        }
    )
}

// ============================================================
// DRAWER SECTION TITLE
// ============================================================

@Composable
fun DrawerSectionTitle(
    title: String
) {

    Text(
        text = title,
        fontSize = 11.sp,
        fontWeight = FontWeight.Bold,
        color = Purple,
        modifier = Modifier.padding(
            start = 24.dp,
            bottom = 8.dp
        )
    )
}

// ============================================================
// DRAWER ITEM
// ============================================================

@Composable
fun DrawerItem(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    title: String,
    selected: Boolean,
    onClick: () -> Unit
) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                horizontal = 12.dp,
                vertical = 4.dp
            )
            .clip(RoundedCornerShape(12.dp))
            .background(
                if (selected) LightPurple
                else Color.Transparent
            )
            .clickable {
                onClick()
            }
            .padding(
                horizontal = 14.dp,
                vertical = 13.dp
            ),

        verticalAlignment = Alignment.CenterVertically
    ) {

        Icon(
            imageVector = icon,
            contentDescription = title,
            tint = if (selected) Purple else DarkText,
            modifier = Modifier.size(21.dp)
        )

        Spacer(
            modifier = Modifier.width(14.dp)
        )

        Text(
            text = title,
            fontSize = 14.sp,
            fontWeight =
                if (selected)
                    FontWeight.Bold
                else
                    FontWeight.Medium,
            color =
                if (selected)
                    Purple
                else
                    DarkText
        )
    }
}

// ============================================================
// DISCOVER SCREEN
// ============================================================

@Composable
fun DiscoverScreen(
    onMealClick: (Meal) -> Unit
) {

    var searchText by remember {
        mutableStateOf("")
    }

    var locationText by remember {
        mutableStateOf("Southport")
    }

    var selectedCuisine by remember {
        mutableStateOf("All Cuisines")
    }

    var selectedSort by remember {
        mutableStateOf("Recommendation")
    }

    var maximumPrice by remember {
        mutableStateOf(20f)
    }

    val cuisines = listOf(
        "All Cuisines",
        "Asian",
        "Fast Food",
        "Japanese",
        "Seafood",
        "Vietnamese"
    )

    val sortOptions = listOf(
        "Recommendation",
        "Price: Low to High",
        "Price: High to Low",
        "Rating"
    )

    val filteredMeals = mealList
        .filter {

            val matchesSearch =
                searchText.isBlank() ||
                        it.name.contains(
                            searchText,
                            ignoreCase = true
                        ) ||
                        it.restaurant.contains(
                            searchText,
                            ignoreCase = true
                        )

            val matchesLocation =
                locationText.isBlank() ||
                        it.location.contains(
                            locationText,
                            ignoreCase = true
                        )

            val matchesCuisine =
                selectedCuisine == "All Cuisines" ||
                        it.cuisine == selectedCuisine

            val matchesPrice =
                it.price <= maximumPrice

            matchesSearch &&
                    matchesLocation &&
                    matchesCuisine &&
                    matchesPrice
        }
        .let { meals ->

            when (selectedSort) {

                "Price: Low to High" ->
                    meals.sortedBy {
                        it.price
                    }

                "Price: High to Low" ->
                    meals.sortedByDescending {
                        it.price
                    }

                "Rating" ->
                    meals.sortedByDescending {
                        it.rating
                    }

                else ->
                    meals.sortedByDescending {
                        it.rating
                    }
            }
        }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(
                rememberScrollState()
            )
            .background(White)
    ) {

        HeroSection()

        Spacer(
            modifier = Modifier.height(12.dp)
        )

        SearchBar(
            searchText = searchText,
            onSearchTextChange = {
                searchText = it
            }
        )

        Spacer(
            modifier = Modifier.height(10.dp)
        )

        LocationSearchBar(
            locationText = locationText,
            onLocationChange = {
                locationText = it
            }
        )

        Spacer(
            modifier = Modifier.height(12.dp)
        )

        FilterSection(
            selectedCuisine = selectedCuisine,
            onCuisineChange = {
                selectedCuisine = it
            },

            selectedSort = selectedSort,
            onSortChange = {
                selectedSort = it
            },

            maximumPrice = maximumPrice,
            onMaximumPriceChange = {
                maximumPrice = it
            },

            onClearFilters = {
                searchText = ""
                locationText = "Southport"
                selectedCuisine = "All Cuisines"
                selectedSort = "Recommendation"
                maximumPrice = 20f
            },

            cuisines = cuisines,
            sortOptions = sortOptions
        )

        Spacer(
            modifier = Modifier.height(16.dp)
        )

        SectionNavigationCard(
            title = "Meal Match",
            description = "Find meals based on your budget and taste.",
            icon = Icons.Default.AutoAwesome
        )

        SectionNavigationCard(
            title = "Deals",
            description = "Explore affordable meals and local offers.",
            icon = Icons.Default.Star
        )

        SectionNavigationCard(
            title = "For Restaurants",
            description = "Promote your restaurant with Local Eats.",
            icon = Icons.Default.Storefront
        )

        Spacer(
            modifier = Modifier.height(20.dp)
        )

        Text(
            text = "Recommended Meals",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = DarkText,
            modifier = Modifier.padding(
                horizontal = 16.dp
            )
        )

        Spacer(
            modifier = Modifier.height(10.dp)
        )

        if (filteredMeals.isEmpty()) {

            EmptyResultsCard()

        } else {

            filteredMeals.forEach { meal ->

                MealCard(
                    meal = meal,
                    onClick = {
                        onMealClick(meal)
                    }
                )

                Spacer(
                    modifier = Modifier.height(12.dp)
                )
            }
        }

        Spacer(
            modifier = Modifier.height(24.dp)
        )
    }
}

// ============================================================
// HERO SECTION
// ============================================================

@Composable
fun HeroSection() {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(LightPurple)
            .padding(
                horizontal = 16.dp,
                vertical = 24.dp
            )
    ) {

        Text(
            text = "Good food.",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = DarkText
        )

        Text(
            text = "Great prices.",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Purple
        )

        Text(
            text = "Right near you.",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = DarkText
        )

        Spacer(
            modifier = Modifier.height(8.dp)
        )

        Text(
            text = "Discover affordable meals and local restaurants around Southport.",
            fontSize = 13.sp,
            color = GreyText,
            lineHeight = 19.sp
        )
    }
}

// ============================================================
// SEARCH BAR
// ============================================================

@Composable
fun SearchBar(
    searchText: String,
    onSearchTextChange: (String) -> Unit
) {

    OutlinedTextField(
        value = searchText,
        onValueChange = onSearchTextChange,
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                horizontal = 16.dp
            ),
        singleLine = true,
        leadingIcon = {

            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = "Search",
                tint = DarkText
            )
        },
        placeholder = {
            Text(
                text = "Search meals or restaurants"
            )
        },
        shape = RoundedCornerShape(10.dp)
    )
}

// ============================================================
// LOCATION SEARCH
// ============================================================

@Composable
fun LocationSearchBar(
    locationText: String,
    onLocationChange: (String) -> Unit
) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                horizontal = 16.dp
            ),
        verticalAlignment = Alignment.CenterVertically
    ) {

        OutlinedTextField(
            value = locationText,
            onValueChange = onLocationChange,
            modifier = Modifier.weight(1f),
            singleLine = true,
            leadingIcon = {

                Icon(
                    imageVector = Icons.Default.LocationOn,
                    contentDescription = "Location",
                    tint = DarkText
                )
            },
            label = {
                Text(
                    text = "Location"
                )
            },
            shape = RoundedCornerShape(10.dp)
        )

        Spacer(
            modifier = Modifier.width(8.dp)
        )

        Button(
            onClick = {},
            modifier = Modifier.size(56.dp),
            contentPadding = PaddingValues(0.dp),
            shape = RoundedCornerShape(50.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Purple
            )
        ) {

            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = "Search location",
                tint = White
            )
        }
    }
}

// ============================================================
// FILTER SECTION
// ============================================================

@Composable
fun FilterSection(
    selectedCuisine: String,
    onCuisineChange: (String) -> Unit,
    selectedSort: String,
    onSortChange: (String) -> Unit,
    maximumPrice: Float,
    onMaximumPriceChange: (Float) -> Unit,
    onClearFilters: () -> Unit,
    cuisines: List<String>,
    sortOptions: List<String>
) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                horizontal = 16.dp
            )
    ) {

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Text(
                text = "Filters",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = DarkText,
                modifier = Modifier.weight(1f)
            )

            Text(
                text = "× Clear Filters",
                color = Purple,
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.clickable {
                    onClearFilters()
                }
            )
        }

        Spacer(
            modifier = Modifier.height(10.dp)
        )

        CuisineDropdown(
            selectedCuisine = selectedCuisine,
            cuisines = cuisines,
            onCuisineChange = onCuisineChange
        )

        Spacer(
            modifier = Modifier.height(10.dp)
        )

        SortDropdown(
            selectedSort = selectedSort,
            sortOptions = sortOptions,
            onSortChange = onSortChange
        )

        Spacer(
            modifier = Modifier.height(14.dp)
        )

        Text(
            text = "Maximum price: A$${String.format("%.2f", maximumPrice)}",
            fontSize = 13.sp,
            fontWeight = FontWeight.Bold,
            color = DarkText
        )

        androidx.compose.material3.Slider(
            value = maximumPrice,
            onValueChange = onMaximumPriceChange,
            valueRange = 5f..20f,
            steps = 14,
            colors = androidx.compose.material3.SliderDefaults.colors(
                thumbColor = Purple,
                activeTrackColor = Purple,
                inactiveTrackColor = LightPurple
            )
        )
    }
}

// ============================================================
// CUISINE DROPDOWN
// ============================================================

@Composable
fun CuisineDropdown(
    selectedCuisine: String,
    cuisines: List<String>,
    onCuisineChange: (String) -> Unit
) {

    var expanded by remember {
        mutableStateOf(false)
    }

    Box(
        modifier = Modifier.fillMaxWidth()
    ) {

        OutlinedButton(
            onClick = {
                expanded = true
            },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(50.dp)
        ) {

            Text(
                text = "Cuisine: $selectedCuisine",
                color = DarkText,
                modifier = Modifier.weight(1f)
            )

            Icon(
                imageVector = Icons.Default.KeyboardArrowDown,
                contentDescription = "Cuisine menu",
                tint = DarkText
            )
        }

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = {
                expanded = false
            }
        ) {

            cuisines.forEach { cuisine ->

                DropdownMenuItem(
                    text = {
                        Text(
                            text = cuisine
                        )
                    },

                    onClick = {

                        onCuisineChange(cuisine)
                        expanded = false
                    }
                )
            }
        }
    }
}

// ============================================================
// SORT DROPDOWN
// ============================================================

@Composable
fun SortDropdown(
    selectedSort: String,
    sortOptions: List<String>,
    onSortChange: (String) -> Unit
) {

    var expanded by remember {
        mutableStateOf(false)
    }

    Box(
        modifier = Modifier.fillMaxWidth()
    ) {

        OutlinedButton(
            onClick = {
                expanded = true
            },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(50.dp)
        ) {

            Text(
                text = "Sort: $selectedSort",
                color = DarkText,
                modifier = Modifier.weight(1f)
            )

            Icon(
                imageVector = Icons.Default.KeyboardArrowDown,
                contentDescription = "Sort menu",
                tint = DarkText
            )
        }

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = {
                expanded = false
            }
        ) {

            sortOptions.forEach { option ->

                DropdownMenuItem(
                    text = {
                        Text(
                            text = option
                        )
                    },

                    onClick = {

                        onSortChange(option)
                        expanded = false
                    }
                )
            }
        }
    }
}

// ============================================================
// MEAL CARD
// ============================================================

@Composable
fun MealCard(
    meal: Meal,
    onClick: () -> Unit
) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                horizontal = 16.dp
            )
            .clickable {
                onClick()
            },
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = White
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 3.dp
        )
    ) {

        Row(
            modifier = Modifier.padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Image(
                painter = painterResource(
                    id = meal.image
                ),
                contentDescription = meal.name,
                modifier = Modifier
                    .size(100.dp)
                    .clip(
                        RoundedCornerShape(12.dp)
                    ),
                contentScale = ContentScale.Crop
            )

            Spacer(
                modifier = Modifier.width(12.dp)
            )

            Column(
                modifier = Modifier.weight(1f)
            ) {

                Text(
                    text = meal.name,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = DarkText
                )

                Spacer(
                    modifier = Modifier.height(4.dp)
                )

                Text(
                    text = meal.restaurant,
                    fontSize = 12.sp,
                    color = GreyText
                )

                Text(
                    text = meal.cuisine,
                    fontSize = 11.sp,
                    color = Purple,
                    fontWeight = FontWeight.Bold
                )

                Spacer(
                    modifier = Modifier.height(6.dp)
                )

                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Text(
                        text = "★ ${meal.rating}",
                        fontSize = 12.sp,
                        color = Yellow,
                        fontWeight = FontWeight.Bold
                    )

                    Spacer(
                        modifier = Modifier.width(8.dp)
                    )

                    Text(
                        text = "A$${String.format("%.2f", meal.price)}",
                        fontSize = 14.sp,
                        color = DarkText,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            Icon(
                imageVector = Icons.Default.ArrowForward,
                contentDescription = "View meal",
                tint = Purple
            )
        }
    }
}

// ============================================================
// EMPTY RESULTS
// ============================================================

@Composable
fun EmptyResultsCard() {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = VeryLightPurple
        ),
        shape = RoundedCornerShape(16.dp)
    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = null,
                tint = Purple,
                modifier = Modifier.size(36.dp)
            )

            Spacer(
                modifier = Modifier.height(10.dp)
            )

            Text(
                text = "No meals found",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = DarkText
            )

            Text(
                text = "Try changing your search or filters.",
                fontSize = 13.sp,
                color = GreyText
            )
        }
    }
}

// ============================================================
// SECTION NAVIGATION CARD
// ============================================================

@Composable
fun SectionNavigationCard(
    title: String,
    description: String,
    icon: androidx.compose.ui.graphics.vector.ImageVector
) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                horizontal = 16.dp,
                vertical = 4.dp
            ),
        colors = CardDefaults.cardColors(
            containerColor = LightPurple
        ),
        shape = RoundedCornerShape(14.dp)
    ) {

        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Icon(
                imageVector = icon,
                contentDescription = title,
                tint = Purple,
                modifier = Modifier.size(25.dp)
            )

            Spacer(
                modifier = Modifier.width(12.dp)
            )

            Column(
                modifier = Modifier.weight(1f)
            ) {

                Text(
                    text = title,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = DarkText
                )

                Text(
                    text = description,
                    fontSize = 11.sp,
                    color = GreyText
                )
            }

            Icon(
                imageVector = Icons.Default.ArrowForward,
                contentDescription = "Open $title",
                tint = Purple
            )
        }
    }
}

// ============================================================
// BACK BUTTON
// ============================================================

@Composable
fun BackButton(
    onClick: () -> Unit
) {

    OutlinedButton(
        onClick = onClick,

        // FIX:
        // Move Back button slightly down.
        modifier = Modifier.padding(
            top = 8.dp
        )
    ) {

        Icon(
            imageVector =
                Icons.AutoMirrored.Filled.ArrowBack,
            contentDescription =
                "Back"
        )

        Spacer(
            modifier =
                Modifier.width(5.dp)
        )

        Text(
            text = "Back"
        )
    }
}

// ============================================================
// MEAL DETAILS SCREEN
// ============================================================

@Composable
fun MealDetailsScreen(
    meal: Meal,
    onBack: () -> Unit,
    onRestaurant: () -> Unit
) {

    val context =
        LocalContext.current

    Column(
        modifier =
            Modifier
                .fillMaxSize()
                .verticalScroll(
                    rememberScrollState()
                )
                .padding(18.dp)
    ) {

        BackButton(
            onClick = onBack
        )

        Spacer(
            modifier =
                Modifier.height(12.dp)
        )

        Image(
            painter =
                painterResource(
                    id = meal.image
                ),

            contentDescription =
                meal.name,

            modifier =
                Modifier
                    .fillMaxWidth()
                    .height(230.dp)
                    .clip(
                        RoundedCornerShape(18.dp)
                    ),

            contentScale =
                ContentScale.Crop
        )

        Spacer(
            modifier =
                Modifier.height(18.dp)
        )

        Text(
            text = meal.name,
            fontSize = 26.sp,
            fontWeight = FontWeight.Bold,
            color = DarkText
        )

        Spacer(
            modifier =
                Modifier.height(5.dp)
        )

        Text(
            text = meal.restaurant,
            fontSize = 15.sp,
            fontWeight = FontWeight.Bold,
            color = Purple
        )

        Spacer(
            modifier =
                Modifier.height(8.dp)
        )

        Row(
            horizontalArrangement =
                Arrangement.spacedBy(8.dp)
        ) {

            InfoPill(
                text = meal.cuisine
            )

            InfoPill(
                text = "★ ${meal.rating}"
            )

            InfoPill(
                text = "A$${String.format("%.2f", meal.price)}"
            )
        }

        Spacer(
            modifier =
                Modifier.height(20.dp)
        )

        DetailInfoCard(
            title = "About this meal",
            description =
                "Affordable ${meal.cuisine.lowercase()} meal available from ${meal.restaurant} in ${meal.location}."
        )

        Spacer(
            modifier =
                Modifier.height(12.dp)
        )

        Button(
            onClick = onRestaurant,
            modifier =
                Modifier.fillMaxWidth(),
            colors =
                ButtonDefaults.buttonColors(
                    containerColor = Purple
                ),
            shape =
                RoundedCornerShape(12.dp)
        ) {

            Icon(
                imageVector =
                    Icons.Default.Restaurant,
                contentDescription =
                    null
            )

            Spacer(
                modifier =
                    Modifier.width(8.dp)
            )

            Text(
                text = "View Restaurant"
            )
        }

        Spacer(
            modifier =
                Modifier.height(10.dp)
        )

        OutlinedButton(
            onClick = {

                val uri =
                    Uri.parse(
                        "geo:0,0?q=${Uri.encode(
                            "${meal.restaurant}, ${meal.location}"
                        )}"
                    )

                context.startActivity(
                    Intent(
                        Intent.ACTION_VIEW,
                        uri
                    )
                )
            },

            modifier =
                Modifier.fillMaxWidth(),

            shape =
                RoundedCornerShape(12.dp)
        ) {

            Icon(
                imageVector =
                    Icons.Default.Map,
                contentDescription =
                    null
            )

            Spacer(
                modifier =
                    Modifier.width(8.dp)
            )

            Text(
                text = "View on Google Maps"
            )
        }

        Spacer(
            modifier =
                Modifier.height(24.dp)
        )
    }
}

// ============================================================
// INFO PILL
// ============================================================

@Composable
fun InfoPill(
    text: String
) {

    Box(
        modifier =
            Modifier
                .clip(
                    RoundedCornerShape(20.dp)
                )
                .background(
                    LightPurple
                )
                .padding(
                    horizontal = 12.dp,
                    vertical = 7.dp
                )
    ) {

        Text(
            text = text,
            color = Purple,
            fontSize = 11.sp,
            fontWeight =
                FontWeight.Bold
        )
    }
}

// ============================================================
// DETAIL INFO CARD
// ============================================================

@Composable
fun DetailInfoCard(
    title: String,
    description: String
) {

    Card(
        modifier =
            Modifier.fillMaxWidth(),

        colors =
            CardDefaults.cardColors(
                containerColor =
                    VeryLightPurple
            ),

        shape =
            RoundedCornerShape(14.dp)
    ) {

        Column(
            modifier =
                Modifier.padding(16.dp)
        ) {

            Text(
                text = title,
                fontSize = 15.sp,
                fontWeight =
                    FontWeight.Bold,
                color = DarkText
            )

            Spacer(
                modifier =
                    Modifier.height(5.dp)
            )

            Text(
                text = description,
                fontSize = 12.sp,
                color = GreyText,
                lineHeight = 18.sp
            )
        }
    }
}

// ============================================================
// RESTAURANT DETAILS SCREEN
// ============================================================

@Composable
fun RestaurantDetailsScreen(
    restaurant: RestaurantInfo,
    onBack: () -> Unit,
    onMealClick: (Meal) -> Unit
) {

    val context =
        LocalContext.current

    val restaurantMeals =
        mealList.filter {
            it.restaurant == restaurant.name
        }

    Column(
        modifier =
            Modifier
                .fillMaxSize()
                .verticalScroll(
                    rememberScrollState()
                )
                .padding(18.dp)
    ) {

        BackButton(
            onClick = onBack
        )

        Spacer(
            modifier =
                Modifier.height(12.dp)
        )

        Text(
            text = restaurant.name,
            fontSize = 25.sp,
            fontWeight = FontWeight.Bold,
            color = DarkText
        )

        Spacer(
            modifier =
                Modifier.height(6.dp)
        )

        InfoPill(
            text = restaurant.cuisine
        )

        Spacer(
            modifier =
                Modifier.height(16.dp)
        )

        DetailInfoCard(
            title = "About the restaurant",
            description = restaurant.description
        )

        Spacer(
            modifier =
                Modifier.height(12.dp)
        )

        DetailInfoCard(
            title = "Address",
            description = restaurant.address
        )

        Spacer(
            modifier =
                Modifier.height(12.dp)
        )

        DetailInfoCard(
            title = "Phone",
            description = restaurant.phone
        )

        Spacer(
            modifier =
                Modifier.height(12.dp)
        )

        DetailInfoCard(
            title = "Opening hours",
            description = restaurant.hours
        )

        Spacer(
            modifier =
                Modifier.height(16.dp)
        )

        Button(
            onClick = {

                val uri =
                    Uri.parse(
                        "geo:0,0?q=${Uri.encode(
                            restaurant.name + ", " +
                                    restaurant.address
                        )}"
                    )

                context.startActivity(
                    Intent(
                        Intent.ACTION_VIEW,
                        uri
                    )
                )
            },

            modifier =
                Modifier.fillMaxWidth(),

            colors =
                ButtonDefaults.buttonColors(
                    containerColor =
                        Purple
                ),

            shape =
                RoundedCornerShape(12.dp)
        ) {

            Icon(
                imageVector =
                    Icons.Default.Map,
                contentDescription =
                    null
            )

            Spacer(
                modifier =
                    Modifier.width(8.dp)
            )

            Text(
                text = "Open in Google Maps"
            )
        }

        Spacer(
            modifier =
                Modifier.height(22.dp)
        )

        Text(
            text = "Meals from this restaurant",
            fontSize = 19.sp,
            fontWeight =
                FontWeight.Bold,
            color = DarkText
        )

        Spacer(
            modifier =
                Modifier.height(10.dp)
        )

        restaurantMeals.forEach { meal ->

            CompactMealCard(
                meal = meal,
                onClick = {
                    onMealClick(meal)
                }
            )

            Spacer(
                modifier =
                    Modifier.height(10.dp)
            )
        }

        Spacer(
            modifier =
                Modifier.height(24.dp)
        )
    }
}

// ============================================================
// COMPACT MEAL CARD
// ============================================================

@Composable
fun CompactMealCard(
    meal: Meal,
    onClick: () -> Unit
) {

    Card(
        modifier =
            Modifier
                .fillMaxWidth()
                .clickable {
                    onClick()
                },

        shape =
            RoundedCornerShape(14.dp),

        colors =
            CardDefaults.cardColors(
                containerColor =
                    White
            ),

        elevation =
            CardDefaults.cardElevation(
                defaultElevation = 2.dp
            )
    ) {

        Row(
            modifier =
                Modifier.padding(10.dp),

            verticalAlignment =
                Alignment.CenterVertically
        ) {

            Image(
                painter =
                    painterResource(
                        id = meal.image
                    ),

                contentDescription =
                    meal.name,

                modifier =
                    Modifier
                        .size(76.dp)
                        .clip(
                            RoundedCornerShape(10.dp)
                        ),

                contentScale =
                    ContentScale.Crop
            )

            Spacer(
                modifier =
                    Modifier.width(10.dp)
            )

            Column(
                modifier =
                    Modifier.weight(1f)
            ) {

                Text(
                    text = meal.name,
                    fontSize = 14.sp,
                    fontWeight =
                        FontWeight.Bold,
                    color = DarkText
                )

                Text(
                    text = meal.cuisine,
                    fontSize = 11.sp,
                    color = GreyText
                )

                Spacer(
                    modifier =
                        Modifier.height(4.dp)
                )

                Text(
                    text =
                        "★ ${meal.rating}   A$${String.format("%.2f", meal.price)}",
                    fontSize = 11.sp,
                    fontWeight =
                        FontWeight.Bold,
                    color = Purple
                )
            }

            Icon(
                imageVector =
                    Icons.Default.ArrowForward,
                contentDescription =
                    "View",
                tint = Purple
            )
        }
    }
}

// ============================================================
// ABOUT SCREEN
// ============================================================

@Composable
fun AboutScreen(
    onBack: () -> Unit
) {

    Column(
        modifier =
            Modifier
                .fillMaxSize()
                .verticalScroll(
                    rememberScrollState()
                )
                .padding(18.dp)
    ) {

        BackButton(
            onClick = onBack
        )

        Spacer(
            modifier =
                Modifier.height(18.dp)
        )

        Image(
            painter =
                painterResource(
                    id = R.drawable.local_eats_logo
                ),

            contentDescription =
                "Local Eats Logo",

            modifier =
                Modifier
                    .size(80.dp)
                    .clip(
                        RoundedCornerShape(18.dp)
                    )
                    .align(
                        Alignment.CenterHorizontally
                    ),

            contentScale =
                ContentScale.Crop
        )

        Spacer(
            modifier =
                Modifier.height(14.dp)
        )

        Text(
            text = "About Local Eats Discovery",
            fontSize = 24.sp,
            fontWeight =
                FontWeight.Bold,
            color = DarkText
        )

        Spacer(
            modifier =
                Modifier.height(8.dp)
        )

        Text(
            text =
                "Good food. Great prices. Right near you.",
            fontSize = 14.sp,
            fontWeight =
                FontWeight.Bold,
            color = Purple
        )

        Spacer(
            modifier =
                Modifier.height(16.dp)
        )

        DetailInfoCard(
            title = "What we do",
            description =
                "Local Eats Discovery helps users discover affordable meals and local restaurants around Southport, Gold Coast, Queensland."
        )

        Spacer(
            modifier =
                Modifier.height(12.dp)
        )

        DetailInfoCard(
            title = "Business type",
            description =
                "Meal discovery and restaurant marketing platform. Local Eats Discovery is not a food delivery service."
        )

        Spacer(
            modifier =
                Modifier.height(12.dp)
        )

        DetailInfoCard(
            title = "Location",
            description =
                "Southport, Gold Coast, Queensland, Australia"
        )

        Spacer(
            modifier =
                Modifier.height(12.dp)
        )

        DetailInfoCard(
            title = "ABN",
            description =
                "Academic prototype – ABN not registered"
        )

        Spacer(
            modifier =
                Modifier.height(12.dp)
        )

        DetailInfoCard(
            title = "Academic prototype",
            description =
                "This application is a university project prototype. Restaurant listings, promotional options and transactions are used for demonstration purposes."
        )

        Spacer(
            modifier =
                Modifier.height(24.dp)
        )
    }
}

// ============================================================
// CONTACT SCREEN
// ============================================================

@Composable
fun ContactScreen(
    onBack: () -> Unit,
    onEnquiryClick: () -> Unit,
    onSendMessageClick: () -> Unit
) {

    val context =
        LocalContext.current

    Column(
        modifier =
            Modifier
                .fillMaxSize()
                .verticalScroll(
                    rememberScrollState()
                )
                .padding(18.dp)
    ) {

        BackButton(
            onClick = onBack
        )

        Spacer(
            modifier =
                Modifier.height(16.dp)
        )

        Text(
            text = "Contact Local Eats Discovery",
            fontSize = 24.sp,
            fontWeight =
                FontWeight.Bold,
            color = DarkText
        )

        Text(
            text =
                "Get in touch with Local Eats Discovery.",
            fontSize = 13.sp,
            color = GreyText
        )

        Spacer(
            modifier =
                Modifier.height(18.dp)
        )

        Button(
            onClick = {

                val intent =
                    Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse(
                            "https://localeatdiscovery.onrender.com/discover"
                        )
                    )

                context.startActivity(intent)
            },

            modifier =
                Modifier.fillMaxWidth(),

            colors =
                ButtonDefaults.buttonColors(
                    containerColor =
                        Purple
                ),

            shape =
                RoundedCornerShape(12.dp)
        ) {

            Icon(
                imageVector =
                    Icons.Default.Web,
                contentDescription =
                    null
            )

            Spacer(
                modifier =
                    Modifier.width(8.dp)
            )

            Text(
                text =
                    "Visit Local Eats Website"
            )
        }

        Spacer(
            modifier =
                Modifier.height(10.dp)
        )

        OutlinedButton(
            onClick = {

                val intent =
                    Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse(
                            "https://www.facebook.com/localeatsdiscovery"
                        )
                    )

                context.startActivity(intent)
            },

            modifier =
                Modifier.fillMaxWidth(),

            shape =
                RoundedCornerShape(12.dp)
        ) {

            Icon(
                imageVector =
                    Icons.Default.Facebook,
                contentDescription =
                    null
            )

            Spacer(
                modifier =
                    Modifier.width(8.dp)
            )

            Text(
                text =
                    "Visit Facebook Page"
            )
        }

        Spacer(
            modifier =
                Modifier.height(18.dp)
        )

        DetailInfoCard(
            title = "Restaurant Partnership",
            description =
                "Restaurants can request a free basic listing or ask about promotional options."
        )

        Spacer(
            modifier =
                Modifier.height(10.dp)
        )

        Button(
            onClick = onEnquiryClick,

            modifier =
                Modifier.fillMaxWidth(),

            colors =
                ButtonDefaults.buttonColors(
                    containerColor =
                        Purple
                ),

            shape =
                RoundedCornerShape(12.dp)
        ) {

            Icon(
                imageVector =
                    Icons.Default.PersonAdd,
                contentDescription =
                    null
            )

            Spacer(
                modifier =
                    Modifier.width(8.dp)
            )

            Text(
                text =
                    "Restaurant Partnership Enquiry"
            )
        }

        Spacer(
            modifier =
                Modifier.height(10.dp)
        )

        OutlinedButton(
            onClick = onSendMessageClick,

            modifier =
                Modifier.fillMaxWidth(),

            shape =
                RoundedCornerShape(12.dp)
        ) {

            Icon(
                imageVector =
                    Icons.Default.Email,
                contentDescription =
                    null
            )

            Spacer(
                modifier =
                    Modifier.width(8.dp)
            )

            Text(
                text =
                    "Send Message"
            )
        }

        Spacer(
            modifier =
                Modifier.height(24.dp)
        )
    }
}

// ============================================================
// RESTAURANT ENQUIRY SCREEN
// ============================================================

@Composable
fun RestaurantEnquiryScreen(
    onBack: () -> Unit,
    onSubmitted: () -> Unit
) {

    var restaurantName by remember {
        mutableStateOf("")
    }

    var contactName by remember {
        mutableStateOf("")
    }

    var email by remember {
        mutableStateOf("")
    }

    var message by remember {
        mutableStateOf("")
    }

    Column(
        modifier =
            Modifier
                .fillMaxSize()
                .verticalScroll(
                    rememberScrollState()
                )
                .padding(18.dp)
    ) {

        BackButton(
            onClick = onBack
        )

        Spacer(
            modifier =
                Modifier.height(16.dp)
        )

        Text(
            text =
                "Restaurant Partnership",
            fontSize = 24.sp,
            fontWeight =
                FontWeight.Bold,
            color = DarkText
        )

        Text(
            text =
                "Tell us about your restaurant and how you would like to work with Local Eats Discovery.",
            fontSize = 13.sp,
            color = GreyText
        )

        Spacer(
            modifier =
                Modifier.height(18.dp)
        )

        OutlinedTextField(
            value = restaurantName,
            onValueChange = {
                restaurantName = it
            },
            modifier =
                Modifier.fillMaxWidth(),
            label = {
                Text("Restaurant name")
            },
            singleLine = true
        )

        Spacer(
            modifier =
                Modifier.height(10.dp)
        )

        OutlinedTextField(
            value = contactName,
            onValueChange = {
                contactName = it
            },
            modifier =
                Modifier.fillMaxWidth(),
            label = {
                Text("Contact person")
            },
            singleLine = true
        )

        Spacer(
            modifier =
                Modifier.height(10.dp)
        )

        OutlinedTextField(
            value = email,
            onValueChange = {
                email = it
            },
            modifier =
                Modifier.fillMaxWidth(),
            label = {
                Text("Email")
            },
            singleLine = true
        )

        Spacer(
            modifier =
                Modifier.height(10.dp)
        )

        OutlinedTextField(
            value = message,
            onValueChange = {
                message = it
            },
            modifier =
                Modifier
                    .fillMaxWidth()
                    .height(130.dp),
            label = {
                Text("Message")
            }
        )

        Spacer(
            modifier =
                Modifier.height(16.dp)
        )

        Button(
            onClick = onSubmitted,
            modifier =
                Modifier.fillMaxWidth(),
            colors =
                ButtonDefaults.buttonColors(
                    containerColor =
                        Purple
                ),
            shape =
                RoundedCornerShape(12.dp)
        ) {

            Icon(
                imageVector =
                    Icons.Default.Email,
                contentDescription =
                    null
            )

            Spacer(
                modifier =
                    Modifier.width(8.dp)
            )

            Text(
                text =
                    "Submit Enquiry"
            )
        }

        Spacer(
            modifier =
                Modifier.height(24.dp)
        )
    }
}

// ============================================================
// ENQUIRY CONFIRMATION
// ============================================================

@Composable
fun EnquiryConfirmationScreen(
    onContinue: () -> Unit
) {

    Column(
        modifier =
            Modifier
                .fillMaxSize()
                .padding(24.dp),
        horizontalAlignment =
            Alignment.CenterHorizontally,
        verticalArrangement =
            Arrangement.Center
    ) {

        Icon(
            imageVector =
                Icons.Default.CheckCircle,
            contentDescription =
                null,
            tint =
                Green,
            modifier =
                Modifier.size(70.dp)
        )

        Spacer(
            modifier =
                Modifier.height(16.dp)
        )

        Text(
            text =
                "Enquiry Submitted",
            fontSize =
                25.sp,
            fontWeight =
                FontWeight.Bold,
            color =
                DarkText
        )

        Spacer(
            modifier =
                Modifier.height(8.dp)
        )

        Text(
            text =
                "Thank you for contacting Local Eats Discovery. Your partnership enquiry has been recorded for this academic prototype.",
            fontSize =
                13.sp,
            color =
                GreyText,
            lineHeight =
                19.sp
        )

        Spacer(
            modifier =
                Modifier.height(22.dp)
        )

        Button(
            onClick =
                onContinue,
            modifier =
                Modifier.fillMaxWidth(),
            colors =
                ButtonDefaults.buttonColors(
                    containerColor =
                        Purple
                ),
            shape =
                RoundedCornerShape(12.dp)
        ) {

            Text(
                text =
                    "Continue"
            )
        }
    }
}

// ============================================================
// PROMOTION SCREEN
// ============================================================

@Composable
fun PromotionScreen(
    onBack: () -> Unit,
    onPaymentClick: () -> Unit
) {

    Column(
        modifier =
            Modifier
                .fillMaxSize()
                .verticalScroll(
                    rememberScrollState()
                )
                .padding(18.dp)
    ) {

        BackButton(
            onClick =
                onBack
        )

        Spacer(
            modifier =
                Modifier.height(16.dp)
        )

        Text(
            text =
                "Promotional Options",
            fontSize =
                24.sp,
            fontWeight =
                FontWeight.Bold,
            color =
                DarkText
        )

        Text(
            text =
                "Promote your restaurant and reach local customers.",
            fontSize =
                13.sp,
            color =
                GreyText
        )

        Spacer(
            modifier =
                Modifier.height(18.dp)
        )

        Card(
            modifier =
                Modifier.fillMaxWidth(),
            colors =
                CardDefaults.cardColors(
                    containerColor =
                        LightPurple
                ),
            shape =
                RoundedCornerShape(16.dp)
        ) {

            Column(
                modifier =
                    Modifier.padding(18.dp)
            ) {

                Text(
                    text =
                        "Local Spotlight",
                    fontSize =
                        18.sp,
                    fontWeight =
                        FontWeight.Bold,
                    color =
                        DarkText
                )

                Text(
                    text =
                        "A$39 / 30 days",
                    fontSize =
                        17.sp,
                    fontWeight =
                        FontWeight.Bold,
                    color =
                        Purple
                )

                Spacer(
                    modifier =
                        Modifier.height(8.dp)
                )

                Text(
                    text =
                        "Demo promotional listing for the academic prototype.",
                    fontSize =
                        12.sp,
                    color =
                        GreyText
                )

                Spacer(
                    modifier =
                        Modifier.height(14.dp)
                )

                Button(
                    onClick =
                        onPaymentClick,
                    modifier =
                        Modifier.fillMaxWidth(),
                    colors =
                        ButtonDefaults.buttonColors(
                            containerColor =
                                Purple
                        ),
                    shape =
                        RoundedCornerShape(12.dp)
                ) {

                    Text(
                        text =
                            "Continue to Demo Payment"
                    )
                }
            }
        }

        Spacer(
            modifier =
                Modifier.height(12.dp)
        )

        DetailInfoCard(
            title =
                "Important",
            description =
                "This payment screen is only a university prototype. No real payment is processed."
        )

        Spacer(
            modifier =
                Modifier.height(24.dp)
        )
    }
}

// ============================================================
// DEMO PAYMENT SCREEN
// ============================================================

@Composable
fun DemoPaymentScreen(
    onBack: () -> Unit,
    onPaymentSuccess: () -> Unit
) {

    var cardName by remember {
        mutableStateOf("")
    }

    var cardNumber by remember {
        mutableStateOf("")
    }

    var expiry by remember {
        mutableStateOf("")
    }

    var cvv by remember {
        mutableStateOf("")
    }

    Column(
        modifier =
            Modifier
                .fillMaxSize()
                .verticalScroll(
                    rememberScrollState()
                )
                .padding(18.dp)
    ) {

        BackButton(
            onClick =
                onBack
        )

        Spacer(
            modifier =
                Modifier.height(16.dp)
        )

        Text(
            text =
                "Demo Payment",
            fontSize =
                24.sp,
            fontWeight =
                FontWeight.Bold,
            color =
                DarkText
        )

        Text(
            text =
                "Local Spotlight – A$39.00",
            fontSize =
                15.sp,
            fontWeight =
                FontWeight.Bold,
            color =
                Purple
        )

        Spacer(
            modifier =
                Modifier.height(10.dp)
        )

        DetailInfoCard(
            title =
                "Academic prototype",
            description =
                "This is a test payment screen. No real money will be charged."
        )

        Spacer(
            modifier =
                Modifier.height(18.dp)
        )

        OutlinedTextField(
            value =
                cardName,
            onValueChange = {
                cardName = it
            },
            modifier =
                Modifier.fillMaxWidth(),
            label =
                {
                    Text("Cardholder name")
                },
            singleLine =
                true
        )

        Spacer(
            modifier =
                Modifier.height(10.dp)
        )

        OutlinedTextField(
            value =
                cardNumber,
            onValueChange = {
                cardNumber = it
            },
            modifier =
                Modifier.fillMaxWidth(),
            label =
                {
                    Text("Card number")
                },
            singleLine =
                true
        )

        Spacer(
            modifier =
                Modifier.height(10.dp)
        )

        Row(
            modifier =
                Modifier.fillMaxWidth(),
            horizontalArrangement =
                Arrangement.spacedBy(10.dp)
        ) {

            OutlinedTextField(
                value =
                    expiry,
                onValueChange = {
                    expiry = it
                },
                modifier =
                    Modifier.weight(1f),
                label =
                    {
                        Text("Expiry")
                    },
                singleLine =
                    true
            )

            OutlinedTextField(
                value =
                    cvv,
                onValueChange = {
                    cvv = it
                },
                modifier =
                    Modifier.weight(1f),
                label =
                    {
                        Text("CVV")
                    },
                singleLine =
                    true
            )
        }

        Spacer(
            modifier =
                Modifier.height(18.dp)
        )

        Button(
            onClick =
                onPaymentSuccess,
            modifier =
                Modifier.fillMaxWidth(),
            colors =
                ButtonDefaults.buttonColors(
                    containerColor =
                        Purple
                ),
            shape =
                RoundedCornerShape(12.dp)
        ) {

            Icon(
                imageVector =
                    Icons.Default.CheckCircle,
                contentDescription =
                    null
            )

            Spacer(
                modifier =
                    Modifier.width(8.dp)
            )

            Text(
                text =
                    "Complete Demo Payment"
            )
        }

        Spacer(
            modifier =
                Modifier.height(24.dp)
        )
    }
}

// ============================================================
// INVOICE SCREEN
// ============================================================

@Composable
fun InvoiceScreen(
    invoiceNumber: String,
    paymentDate: String,
    onBack: () -> Unit
) {

    Column(
        modifier =
            Modifier
                .fillMaxSize()
                .verticalScroll(
                    rememberScrollState()
                )
                .padding(18.dp)
    ) {

        BackButton(
            onClick =
                onBack
        )

        Spacer(
            modifier =
                Modifier.height(16.dp)
        )

        Text(
            text =
                "Payment Invoice",
            fontSize =
                24.sp,
            fontWeight =
                FontWeight.Bold,
            color =
                DarkText
        )

        Spacer(
            modifier =
                Modifier.height(14.dp)
        )

        Card(
            modifier =
                Modifier.fillMaxWidth(),
            colors =
                CardDefaults.cardColors(
                    containerColor =
                        White
                ),
            elevation =
                CardDefaults.cardElevation(
                    defaultElevation =
                        4.dp
                ),
            shape =
                RoundedCornerShape(16.dp)
        ) {

            Column(
                modifier =
                    Modifier.padding(20.dp)
            ) {

                Text(
                    text =
                        "Local Eats Discovery",
                    fontSize =
                        20.sp,
                    fontWeight =
                        FontWeight.Bold,
                    color =
                        Purple
                )

                Text(
                    text =
                        "Local Spotlight",
                    fontSize =
                        14.sp,
                    fontWeight =
                        FontWeight.Bold,
                    color =
                        DarkText
                )

                Spacer(
                    modifier =
                        Modifier.height(14.dp)
                )

                Text(
                    text =
                        "Invoice: $invoiceNumber",
                    fontSize =
                        12.sp,
                    color =
                        GreyText
                )

                Text(
                    text =
                        "Date: $paymentDate",
                    fontSize =
                        12.sp,
                    color =
                        GreyText
                )

                Spacer(
                    modifier =
                        Modifier.height(18.dp)
                )

                Row(
                    modifier =
                        Modifier.fillMaxWidth()
                ) {

                    Text(
                        text =
                            "Local Spotlight – 30 days",
                        modifier =
                            Modifier.weight(1f),
                        fontSize =
                            13.sp,
                        color =
                            DarkText
                    )

                    Text(
                        text =
                            "A$39.00",
                        fontSize =
                            13.sp,
                        fontWeight =
                            FontWeight.Bold,
                        color =
                            DarkText
                    )
                }

                Spacer(
                    modifier =
                        Modifier.height(16.dp)
                )

                Divider()

                Spacer(
                    modifier =
                        Modifier.height(12.dp)
                )

                Row(
                    modifier =
                        Modifier.fillMaxWidth()
                ) {

                    Text(
                        text =
                            "Total Paid",
                        modifier =
                            Modifier.weight(1f),
                        fontSize =
                            15.sp,
                        fontWeight =
                            FontWeight.Bold,
                        color =
                            DarkText
                    )

                    Text(
                        text =
                            "A$39.00",
                        fontSize =
                            15.sp,
                        fontWeight =
                            FontWeight.Bold,
                        color =
                            Purple
                    )
                }

                Spacer(
                    modifier =
                        Modifier.height(12.dp)
                )

                Text(
                    text =
                        "Status: PAID",
                    fontSize =
                        12.sp,
                    fontWeight =
                        FontWeight.Bold,
                    color =
                        Green
                )

                Spacer(
                    modifier =
                        Modifier.height(18.dp)
                )

                Text(
                    text =
                        "Academic prototype – no real payment was processed.",
                    fontSize =
                        11.sp,
                    color =
                        GreyText
                )
            }
        }

        Spacer(
            modifier =
                Modifier.height(24.dp)
        )
    }
}

// ============================================================
// MEAL MATCH SCREEN
// ============================================================

@Composable
fun MealMatchScreen(
    onBack: () -> Unit,
    onMealClick: (Meal) -> Unit
) {

    var selectedCuisine by remember {
        mutableStateOf("Any")
    }

    var selectedBudget by remember {
        mutableStateOf("A$15")
    }

    val cuisines =
        listOf(
            "Any",
            "Asian",
            "Fast Food",
            "Japanese",
            "Seafood",
            "Vietnamese"
        )

    val budgets =
        listOf(
            "A$10",
            "A$12",
            "A$15",
            "A$20"
        )

    val matchedMeals =
        mealList.filter {

            val cuisineMatch =
                selectedCuisine == "Any" ||
                        it.cuisine == selectedCuisine

            val budget =
                selectedBudget
                    .replace("A$", "")
                    .toDouble()

            cuisineMatch &&
                    it.price <= budget
        }

    Column(
        modifier =
            Modifier
                .fillMaxSize()
                .verticalScroll(
                    rememberScrollState()
                )
                .padding(18.dp)
    ) {

        BackButton(
            onClick =
                onBack
        )

        Spacer(
            modifier =
                Modifier.height(16.dp)
        )

        Text(
            text =
                "Meal Match",
            fontSize =
                25.sp,
            fontWeight =
                FontWeight.Bold,
            color =
                DarkText
        )

        Text(
            text =
                "Find affordable meals based on your taste and budget.",
            fontSize =
                13.sp,
            color =
                GreyText
        )

        Spacer(
            modifier =
                Modifier.height(18.dp)
        )

        Text(
            text =
                "Cuisine",
            fontSize =
                13.sp,
            fontWeight =
                FontWeight.Bold,
            color =
                DarkText
        )

        Spacer(
            modifier =
                Modifier.height(6.dp)
        )

        Row(
            modifier =
                Modifier.fillMaxWidth(),
            horizontalArrangement =
                Arrangement.spacedBy(6.dp)
        ) {

            cuisines.take(3).forEach { cuisine ->

                FilterChip(
                    selected =
                        selectedCuisine == cuisine,
                    onClick = {
                        selectedCuisine = cuisine
                    },
                    label = {
                        Text(cuisine)
                    }
                )
            }
        }

        Row(
            modifier =
                Modifier.fillMaxWidth(),
            horizontalArrangement =
                Arrangement.spacedBy(6.dp)
        ) {

            cuisines.drop(3).forEach { cuisine ->

                FilterChip(
                    selected =
                        selectedCuisine == cuisine,
                    onClick = {
                        selectedCuisine = cuisine
                    },
                    label = {
                        Text(cuisine)
                    }
                )
            }
        }

        Spacer(
            modifier =
                Modifier.height(16.dp)
        )

        Text(
            text =
                "Budget",
            fontSize =
                13.sp,
            fontWeight =
                FontWeight.Bold,
            color =
                DarkText
        )

        Spacer(
            modifier =
                Modifier.height(6.dp)
        )

        Row(
            modifier =
                Modifier.fillMaxWidth(),
            horizontalArrangement =
                Arrangement.spacedBy(6.dp)
        ) {

            budgets.forEach { budget ->

                FilterChip(
                    selected =
                        selectedBudget == budget,
                    onClick = {
                        selectedBudget = budget
                    },
                    label = {
                        Text(budget)
                    }
                )
            }
        }

        Spacer(
            modifier =
                Modifier.height(18.dp)
        )

        Text(
            text =
                "${matchedMeals.size} meals matched",
            fontSize =
                16.sp,
            fontWeight =
                FontWeight.Bold,
            color =
                DarkText
        )

        Spacer(
            modifier =
                Modifier.height(10.dp)
        )

        matchedMeals.forEach { meal ->

            MealCard(
                meal =
                    meal,
                onClick = {
                    onMealClick(meal)
                }
            )

            Spacer(
                modifier =
                    Modifier.height(10.dp)
            )
        }

        Spacer(
            modifier =
                Modifier.height(24.dp)
        )
    }
}

// ============================================================
// DEALS SCREEN
// ============================================================

@Composable
fun DealsScreen(
    onBack: () -> Unit,
    onMealClick: (Meal) -> Unit
) {

    val deals =
        mealList
            .filter {
                it.price <= 15.0
            }
            .sortedBy {
                it.price
            }

    Column(
        modifier =
            Modifier
                .fillMaxSize()
                .verticalScroll(
                    rememberScrollState()
                )
                .padding(18.dp)
    ) {

        BackButton(
            onClick =
                onBack
        )

        Spacer(
            modifier =
                Modifier.height(16.dp)
        )

        Text(
            text =
                "Deals",
            fontSize =
                25.sp,
            fontWeight =
                FontWeight.Bold,
            color =
                DarkText
        )

        Text(
            text =
                "Affordable local meals under A$15.",
            fontSize =
                13.sp,
            color =
                GreyText
        )

        Spacer(
            modifier =
                Modifier.height(16.dp)
        )

        Card(
            modifier =
                Modifier.fillMaxWidth(),
            colors =
                CardDefaults.cardColors(
                    containerColor =
                        LightPurple
                ),
            shape =
                RoundedCornerShape(16.dp)
        ) {

            Column(
                modifier =
                    Modifier.padding(16.dp)
            ) {

                Text(
                    text =
                        "Budget Picks",
                    fontSize =
                        18.sp,
                    fontWeight =
                        FontWeight.Bold,
                    color =
                        Purple
                )

                Text(
                    text =
                        "Great meals without spending too much.",
                    fontSize =
                        12.sp,
                    color =
                        GreyText
                )
            }
        }

        Spacer(
            modifier =
                Modifier.height(14.dp)
        )

        deals.forEach { meal ->

            MealCard(
                meal =
                    meal,
                onClick = {
                    onMealClick(meal)
                }
            )

            Spacer(
                modifier =
                    Modifier.height(10.dp)
            )
        }

        Spacer(
            modifier =
                Modifier.height(24.dp)
        )
    }
}

// ============================================================
// RESTAURANTS SCREEN
// ============================================================

@Composable
fun RestaurantsScreen(
    onBack: () -> Unit,
    onRestaurantClick: (RestaurantInfo) -> Unit,
    onEnquiryClick: () -> Unit,
    onPromotionClick: () -> Unit
) {

    Column(
        modifier =
            Modifier
                .fillMaxSize()
                .verticalScroll(
                    rememberScrollState()
                )
                .padding(18.dp)
    ) {

        BackButton(
            onClick =
                onBack
        )

        Spacer(
            modifier =
                Modifier.height(16.dp)
        )

        Text(
            text =
                "For Restaurants",
            fontSize =
                25.sp,
            fontWeight =
                FontWeight.Bold,
            color =
                DarkText
        )

        Text(
            text =
                "Promote your restaurant and reach local customers.",
            fontSize =
                13.sp,
            color =
                GreyText
        )

        Spacer(
            modifier =
                Modifier.height(18.dp)
        )

        DetailInfoCard(
            title =
                "Free Basic Listing",
            description =
                "Restaurants can request a free basic listing on Local Eats Discovery."
        )

        Spacer(
            modifier =
                Modifier.height(12.dp)
        )

        DetailInfoCard(
            title =
                "Local Spotlight",
            description =
                "Promotional placement from A$39 for 30 days in this academic prototype."
        )

        Spacer(
            modifier =
                Modifier.height(14.dp)
        )

        Button(
            onClick =
                onPromotionClick,
            modifier =
                Modifier.fillMaxWidth(),
            colors =
                ButtonDefaults.buttonColors(
                    containerColor =
                        Purple
                ),
            shape =
                RoundedCornerShape(12.dp)
        ) {

            Icon(
                imageVector =
                    Icons.Default.Storefront,
                contentDescription =
                    null
            )

            Spacer(
                modifier =
                    Modifier.width(8.dp)
            )

            Text(
                text =
                    "View Promotional Options"
            )
        }

        Spacer(
            modifier =
                Modifier.height(10.dp)
        )

        OutlinedButton(
            onClick =
                onEnquiryClick,
            modifier =
                Modifier.fillMaxWidth(),
            shape =
                RoundedCornerShape(12.dp)
        ) {

            Icon(
                imageVector =
                    Icons.Default.PersonAdd,
                contentDescription =
                    null
            )

            Spacer(
                modifier =
                    Modifier.width(8.dp)
            )

            Text(
                text =
                    "Restaurant Partnership Enquiry"
            )
        }

        Spacer(
            modifier =
                Modifier.height(20.dp)
        )

        Text(
            text =
                "Featured Restaurants",
            fontSize =
                19.sp,
            fontWeight =
                FontWeight.Bold,
            color =
                DarkText
        )

        Spacer(
            modifier =
                Modifier.height(10.dp)
        )

        restaurantList.forEach { restaurant ->

            Card(
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .clickable {
                            onRestaurantClick(restaurant)
                        },

                shape =
                    RoundedCornerShape(14.dp),

                colors =
                    CardDefaults.cardColors(
                        containerColor =
                            White
                    ),

                elevation =
                    CardDefaults.cardElevation(
                        defaultElevation =
                            2.dp
                    )
            ) {

                Column(
                    modifier =
                        Modifier.padding(16.dp)
                ) {

                    Row(
                        verticalAlignment =
                            Alignment.CenterVertically
                    ) {

                        Icon(
                            imageVector =
                                Icons.Default.Restaurant,
                            contentDescription =
                                null,
                            tint =
                                Purple,
                            modifier =
                                Modifier.size(28.dp)
                        )

                        Spacer(
                            modifier =
                                Modifier.width(10.dp)
                        )

                        Column(
                            modifier =
                                Modifier.weight(1f)
                        ) {

                            Text(
                                text =
                                    restaurant.name,
                                fontSize =
                                    15.sp,
                                fontWeight =
                                    FontWeight.Bold,
                                color =
                                    DarkText
                            )

                            Text(
                                text =
                                    restaurant.cuisine,
                                fontSize =
                                    11.sp,
                                color =
                                    Purple
                            )
                        }

                        Icon(
                            imageVector =
                                Icons.Default.ArrowForward,
                            contentDescription =
                                null,
                            tint =
                                Purple
                        )
                    }

                    Spacer(
                        modifier =
                            Modifier.height(8.dp)
                    )

                    Text(
                        text =
                            restaurant.address,
                        fontSize =
                            11.sp,
                        color =
                            GreyText
                    )
                }
            }

            Spacer(
                modifier =
                    Modifier.height(10.dp)
            )
        }

        Spacer(
            modifier =
                Modifier.height(24.dp)
        )
    }
}

// ============================================================
// SEND MESSAGE SCREEN
// ============================================================

@Composable
fun SendMessageScreen(
    onBack: () -> Unit,
    onSent: () -> Unit
) {

    var name by remember {
        mutableStateOf("")
    }

    var email by remember {
        mutableStateOf("")
    }

    var message by remember {
        mutableStateOf("")
    }

    Column(
        modifier =
            Modifier
                .fillMaxSize()
                .verticalScroll(
                    rememberScrollState()
                )
                .padding(18.dp)
    ) {

        BackButton(
            onClick =
                onBack
        )

        Spacer(
            modifier =
                Modifier.height(16.dp)
        )

        Text(
            text =
                "Send Message",
            fontSize =
                25.sp,
            fontWeight =
                FontWeight.Bold,
            color =
                DarkText
        )

        Text(
            text =
                "Send a message to Local Eats Discovery.",
            fontSize =
                13.sp,
            color =
                GreyText
        )

        Spacer(
            modifier =
                Modifier.height(18.dp)
        )

        OutlinedTextField(
            value =
                name,
            onValueChange = {
                name = it
            },
            modifier =
                Modifier.fillMaxWidth(),
            label =
                {
                    Text("Your name")
                },
            singleLine =
                true
        )

        Spacer(
            modifier =
                Modifier.height(10.dp)
        )

        OutlinedTextField(
            value =
                email,
            onValueChange = {
                email = it
            },
            modifier =
                Modifier.fillMaxWidth(),
            label =
                {
                    Text("Email")
                },
            singleLine =
                true
        )

        Spacer(
            modifier =
                Modifier.height(10.dp)
        )

        OutlinedTextField(
            value =
                message,
            onValueChange = {
                message = it
            },
            modifier =
                Modifier
                    .fillMaxWidth()
                    .height(140.dp),
            label =
                {
                    Text("Message")
                }
        )

        Spacer(
            modifier =
                Modifier.height(16.dp)
        )

        Button(
            onClick =
                onSent,
            modifier =
                Modifier.fillMaxWidth(),
            colors =
                ButtonDefaults.buttonColors(
                    containerColor =
                        Purple
                ),
            shape =
                RoundedCornerShape(12.dp)
        ) {

            Icon(
                imageVector =
                    Icons.Default.Email,
                contentDescription =
                    null
            )

            Spacer(
                modifier =
                    Modifier.width(8.dp)
            )

            Text(
                text =
                    "Send Message"
            )
        }

        Spacer(
            modifier =
                Modifier.height(24.dp)
        )
    }
}

// ============================================================
// SAVED MEALS SCREEN
// ============================================================

@Composable
fun SavedMealsScreen(
    onBack: () -> Unit,
    onMealClick: (Meal) -> Unit
) {

    Column(
        modifier =
            Modifier
                .fillMaxSize()
                .verticalScroll(
                    rememberScrollState()
                )
                .padding(18.dp)
    ) {

        BackButton(
            onClick =
                onBack
        )

        Spacer(
            modifier =
                Modifier.height(16.dp)
        )

        Text(
            text =
                "Saved Meals",
            fontSize =
                25.sp,
            fontWeight =
                FontWeight.Bold,
            color =
                DarkText
        )

        Spacer(
            modifier =
                Modifier.height(6.dp)
        )

        Text(
            text =
                "Your saved meals will appear here.",
            fontSize =
                13.sp,
            color =
                GreyText
        )

        Spacer(
            modifier =
                Modifier.height(18.dp)
        )

        DetailInfoCard(
            title =
                "Saved meals",
            description =
                "This academic prototype keeps the saved meals area ready for future local storage integration."
        )

        Spacer(
            modifier =
                Modifier.height(24.dp)
        )
    }
}

// ============================================================
// SETTINGS SCREEN
// ============================================================

@Composable
fun SettingsScreen(
    onBack: () -> Unit
) {

    var notificationsEnabled by remember {
        mutableStateOf(true)
    }

    var darkModeEnabled by remember {
        mutableStateOf(false)
    }

    Column(
        modifier =
            Modifier
                .fillMaxSize()
                .verticalScroll(
                    rememberScrollState()
                )
                .padding(18.dp)
    ) {

        BackButton(
            onClick =
                onBack
        )

        Spacer(
            modifier =
                Modifier.height(16.dp)
        )

        Text(
            text =
                "Settings",
            fontSize =
                25.sp,
            fontWeight =
                FontWeight.Bold,
            color =
                DarkText
        )

        Spacer(
            modifier =
                Modifier.height(18.dp)
        )

        SettingsRow(
            title =
                "Notifications",
            description =
                "Receive Local Eats updates.",
            checked =
                notificationsEnabled,
            onCheckedChange = {
                notificationsEnabled = it
            }
        )

        Spacer(
            modifier =
                Modifier.height(10.dp)
        )

        SettingsRow(
            title =
                "Dark mode",
            description =
                "Appearance option for future versions.",
            checked =
                darkModeEnabled,
            onCheckedChange = {
                darkModeEnabled = it
            }
        )

        Spacer(
            modifier =
                Modifier.height(24.dp)
        )

        DetailInfoCard(
            title =
                "Account",
            description =
                "Local Eats Discovery academic prototype."
        )

        Spacer(
            modifier =
                Modifier.height(24.dp)
        )
    }
}

// ============================================================
// SETTINGS ROW
// ============================================================

@Composable
fun SettingsRow(
    title: String,
    description: String,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit
) {

    Card(
        modifier =
            Modifier.fillMaxWidth(),
        colors =
            CardDefaults.cardColors(
                containerColor =
                    White
            ),
        elevation =
            CardDefaults.cardElevation(
                defaultElevation =
                    2.dp
            ),
        shape =
            RoundedCornerShape(14.dp)
    ) {

        Row(
            modifier =
                Modifier.padding(16.dp),
            verticalAlignment =
                Alignment.CenterVertically
        ) {

            Column(
                modifier =
                    Modifier.weight(1f)
            ) {

                Text(
                    text =
                        title,
                    fontSize =
                        15.sp,
                    fontWeight =
                        FontWeight.Bold,
                    color =
                        DarkText
                )

                Text(
                    text =
                        description,
                    fontSize =
                        11.sp,
                    color =
                        GreyText
                )
            }

            Switch(
                checked =
                    checked,
                onCheckedChange =
                    onCheckedChange
            )
        }
    }
}
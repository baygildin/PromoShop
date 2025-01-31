package com.sbaygildin.promoshop.feature.promo

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.sbaygildin.promoshop.core.R
import com.sbaygildin.promoshop.core.logging.EventLogger
import com.sbaygildin.promoshop.core.ui.SFUIText
import com.sbaygildin.promoshop.domain.model.AdressSuggestion
import com.sbaygildin.promoshop.domain.repository.AdressRepository
import com.sbaygildin.promoshop.domain.usecase.SearchAdressUseCase
import com.sbaygildin.promoshop.feature.components.AddressSearchModal
import com.sbaygildin.promoshop.feature.components.DrawerContent
import com.sbaygildin.promoshop.feature.components.DropDownAdressList
import com.sbaygildin.promoshop.feature.components.ProductSearchBar
import com.sbaygildin.promoshop.feature.promo.ui.CatalogSections
import com.sbaygildin.promoshop.feature.promo.ui.DiscountProductsSection
import com.sbaygildin.promoshop.feature.promo.ui.MealsCategoriesSection
import com.sbaygildin.promoshop.feature.promo.ui.PromoBannersSection
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PromoScreen(
    viewModel: PromoViewModel = hiltViewModel(),
) {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    val selectedAddress = viewModel.selectedAdress.collectAsState()
    var showAddressSearch by remember { mutableStateOf(false) }

    if (showAddressSearch) {
        AddressSearchModal(
            viewModel = viewModel,
            onDismiss = { showAddressSearch = false }
        )
    }

    ModalNavigationDrawer(
        drawerContent = {
            Surface(
                modifier = Modifier
                    .fillMaxHeight()
                    .width(337.dp),
                shape = RoundedCornerShape(topEnd = 16.dp, bottomEnd = 16.dp),
                color = MaterialTheme.colorScheme.surface
            ) {
                DrawerContent {
                    scope.launch { drawerState.close() }
                    EventLogger.logClick("Меню закрыто")
                }
            }
        },
        drawerState = drawerState,
        scrimColor = Color(0x80000000),
        modifier = Modifier.fillMaxWidth()
    ) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        DropDownAdressList(
                            selectedAddress = selectedAddress,
                            onAddressClick = {
                                showAddressSearch = true
                                EventLogger.logClick("Нажат выбор адреса")
                            }

                        )
                    },
                    navigationIcon = {
                        IconButton(onClick = {
                            scope.launch { drawerState.open() }
                            EventLogger.logClick("Меню открыто")
                        }) {
                            Icon(Icons.Default.Menu, contentDescription = "Menu")
                        }
                    }
                )
            },
            containerColor = MaterialTheme.colorScheme.background
        ) { innerPadding ->

            LazyColumn(
                modifier = Modifier.padding(innerPadding),
                contentPadding = PaddingValues(bottom = 16.dp)
            ) {
                item {


                    Row(
                        Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 15.dp, vertical = 8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        ProductSearchBar(
                            modifier = Modifier
                                .width(335.dp)
                                .clickable {
                                    EventLogger.logClick("Нажат поиск товаров")
                                }
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Image(
                            painter = painterResource(id = R.drawable.ic_heart),
                            contentDescription = null,
                            modifier = Modifier
                                .size(35.dp)
                                .align(Alignment.CenterVertically)
                                .clickable {
                                    EventLogger.logClick("Добавление в избранное")
                                }
                        )
                    }
                }
                item { MealsCategoriesSection() }
                item { Spacer(modifier = Modifier.height(24.dp)) }
                item { PromoBannersSection() }
                item { Spacer(modifier = Modifier.height(24.dp)) }
                item {
                    Row(
                        Modifier
                            .fillMaxWidth()
                            .padding(bottom = 16.dp),
                        horizontalArrangement = Arrangement.SpaceBetween

                    ) {
                        Text(
                            stringResource(R.string.section_promotions),
                            modifier = Modifier
                                .padding(start = 15.dp)
                                .align(Alignment.CenterVertically)
                                .clickable {
                                    EventLogger.logClick("Нажат раздел 'Акции'")
                                },
                            style = TextStyle(


                                fontSize = 20.sp,
                                lineHeight = 25.sp,
                                fontFamily = SFUIText,
                                fontWeight = FontWeight.W400,
                                color = Color.Black
                            ),

                            )
                        Box(
                            modifier = Modifier
                                .background(
                                    Color(0xFFF4F4F4),
                                    RoundedCornerShape(12.dp)
                                )
                                .padding(end = 15.dp)
                                .align(Alignment.CenterVertically)
                                .clickable {
                                    EventLogger.logClick("Нажато Смотреть всё")
                                }
                        )
                        {
                            Text(
                                stringResource(R.string.section_watch_all),
                                style = TextStyle(
                                    fontSize = 12.sp,
                                    lineHeight = 20.sp,
                                    fontFamily = SFUIText,
                                    fontWeight = FontWeight.W400,
                                    color = Color.Black
                                )
                            )
                        }

                    }
                }

                item { DiscountProductsSection() }
                item {
                    Text(
                        stringResource(R.string.section_catalog),
                        modifier = Modifier
                            .padding(start = 15.dp)
                            .clickable {
                                EventLogger.logClick("Нажат раздел 'Каталог'")
                            },
                        style = TextStyle(
                            fontSize = 20.sp,
                            lineHeight = 25.sp,
                            fontFamily = SFUIText,
                            fontWeight = FontWeight.W400,
                            color = Color.Black
                        )
                    )
                }
                item { Spacer(modifier = Modifier.height(16.dp)) }
                item {
                    CatalogSections()
                }
            }
        }
        if (showAddressSearch) {
            AddressSearchModal(
                viewModel = viewModel,
                onDismiss = {
                    showAddressSearch = false
                    EventLogger.logClick("Закрыто окно поиска адреса")
                }
            )
        }

    }
}

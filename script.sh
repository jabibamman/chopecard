#!/bin/bash

# Chemin de base pour la création des fichiers
BASE_DIR="app/src/main/java/com/chopecard/"

# Liste des chemins relatifs et fichiers à créer
declare -a FILES=(
    "domain/models/Collector.kt"
    "domain/models/Seller.kt"
    "domain/models/Product.kt"
    "domain/models/Store.kt"
    "domain/models/Card.kt"
    "domain/models/Extension.kt"
    "domain/usecases/FetchProductsUseCase.kt"
    "domain/usecases/ManageFavoritesUseCase.kt"
    "domain/usecases/UpdateStockUseCase.kt"
    "data/repository/ProductRepositoryImpl.kt"
    "data/repository/StoreRepositoryImpl.kt"
    "data/network/ApiService.kt"
    "data/network/ApiHelper.kt"
    "presentation/view/CollectorView.kt"
    "presentation/view/SellerView.kt"
    "presentation/view/ProductDetailView.kt"
    "presentation/view/StoreViewModel.kt"
    "presentation/viewModel/CollectorViewModel.kt"
    "presentation/viewModel/SellerViewModel.kt"
    "di/AppModule.kt"
)

# Créer chaque fichier s'il n'existe pas déjà
for file in "${FILES[@]}"; do
    # Chemin complet du fichier
    FULL_PATH="$BASE_DIR$file"

    # Créer le répertoire si nécessaire
    mkdir -p "$(dirname "$FULL_PATH")"

    # Créer le fichier s'il n'existe pas
    if [ ! -f "$FULL_PATH" ]; then
        echo "Création du fichier: $FULL_PATH"
        touch "$FULL_PATH"
    else
        echo "Le fichier existe déjà: $FULL_PATH"
    fi
done


### Exercice Lambda 2 

1. Définir une classe Produit :
- La classe Produit contient les attributs nom, prix, quantite.

2. Définir des interfaces fonctionnelles personnalisées :

- FiltreProduit : Cette interface fonctionnelle prend un objet Produit et retourne un boolean pour indiquer si un produit passe le filtre.
- TransformationProduit : Cette interface fonctionnelle prend un Produit et retourne un Produit transformé.
- OperationProduit : Cette interface fonctionnelle permet d'appliquer une série d'opérations successives sur un Produit.

3. Exécuter des transformations et des filtres avec des lambdas :

- Crée filtre pour trouver les produits qui sont en stock (quantité > 0).
- Crée filtre pour trouver les produits par leur nom.
- Crée une transformation pour augmenter le prix des produits.
- Crée une transformation pour diminuer le prix des produits.
- Crée une transformation pour restocker un produit.
- Crée une transformation pour diminuer le stock un produit.
- Crée une série d'opérations successives diminuer le prix, puis ajouter un préfixe au nom du produit pour marquer une promotion.


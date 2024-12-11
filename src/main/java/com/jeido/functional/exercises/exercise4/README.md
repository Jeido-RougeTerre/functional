## Exercice Stream 2

- Définir une classe Commande :

```Java
class Commande {
    private int id;
    private String client;
    private List<String> articles;
    private double montantTotal;
    private boolean estLivree;
}
```

```Java
    public static void main(String[] args) {
        // Liste de commandes
        List<Commande> commandes = Arrays.asList(
                new Commande(1, "Alice", Arrays.asList("Ordinateur", "Souris"), 1200.50, true),
                new Commande(2, "Bob", Arrays.asList("Clavier", "Écran"), 300.75, false),
                new Commande(3, "Charlie", Arrays.asList("Imprimante"), 150.00, true),
                new Commande(4, "Alice", Arrays.asList("USB", "Casque"), 75.50, false),
                new Commande(5, "Bob", Arrays.asList("Tablette"), 200.00, true)
        );
    }
```


-  Objectifs de l'exercice

Vous devez implémenter les différentes tâches suivantes en utilisant des **Streams** :

1. Afficher toutes les commandes.
2. Récupérer et afficher le nom des clients de chaque commande.
3. Calculer et afficher le montant total de toutes les commandes.
4. Afficher les articles et le prix total pour chaque commande.
5. Lister les commandes livrées : Utiliser Stream.filter pour filtrer les commandes livrées.
6. Calculer le montant total par client : Grouper les commandes par client avec Collectors.groupingBy et calculer la somme des montants
7. Obtenir les articles uniques commandés 
8. Vérifier si tous les clients ont une commande livrée : Grouper par client et utiliser Stream.allMatch pour vérifier la condition.
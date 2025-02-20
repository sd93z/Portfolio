import random
import tkinter as tk
from tkinter import messagebox

class JeuDeDevine:
    def __init__(self, root):
        self.root = root
        self.root.title("Jeu de Devinettes")
        self.root.geometry("400x350")
        self.root.resizable(False, False)
        
        # Générer le nombre secret
        self.nombre_secret = random.randint(1, 100)
        self.essais = 0
        self.victoires = 0  # Compteur de victoires
        
        # Widgets de l'interface
        self.titre = tk.Label(root, text="Devinez le nombre !", font=("Helvetica", 16), pady=10)
        self.titre.pack()
        
        self.instructions = tk.Label(root, text="Je pense à un nombre entre 1 et 100.\nEssayez de le deviner !", font=("Helvetica", 12))
        self.instructions.pack()
        
        self.entree = tk.Entry(root, font=("Helvetica", 14), justify="center")
        self.entree.pack(pady=10)
        
        self.bouton_deviner = tk.Button(root, text="Deviner", font=("Helvetica", 12), command=self.verifier)
        self.bouton_deviner.pack(pady=10)
        
        self.resultat = tk.Label(root, text="", font=("Helvetica", 12))
        self.resultat.pack()
        
        self.bouton_reset = tk.Button(root, text="Rejouer", font=("Helvetica", 12), command=self.reinitialiser)
        self.bouton_reset.pack(pady=10)
        self.bouton_reset["state"] = "disabled"  # Désactivé au début

        # Compteur de victoires
        self.compteur_victoires = tk.Label(root, text=f"Victoires : {self.victoires}", font=("Helvetica", 12), fg="green")
        self.compteur_victoires.pack(pady=10)

    def verifier(self):
        try:
            devine = int(self.entree.get())
            self.essais += 1
            
            if devine < self.nombre_secret:
                self.resultat.config(text="C'est plus grand !", fg="blue")
            elif devine > self.nombre_secret:
                self.resultat.config(text="C'est plus petit !", fg="blue")
            else:
                messagebox.showinfo("Félicitations !", f"Bravo ! Vous avez trouvé le nombre {self.nombre_secret} en {self.essais} essais.")
                self.bouton_deviner["state"] = "disabled"
                self.bouton_reset["state"] = "normal"
                self.resultat.config(text="")
                self.victoires += 1
                self.compteur_victoires.config(text=f"Victoires : {self.victoires}")
        except ValueError:
            messagebox.showerror("Erreur", "Veuillez entrer un nombre valide.")

    def reinitialiser(self):
        self.nombre_secret = random.randint(1, 100)
        self.essais = 0
        self.entree.delete(0, tk.END)
        self.resultat.config(text="")
        self.bouton_deviner["state"] = "normal"
        self.bouton_reset["state"] = "disabled"


if __name__ == "__main__":
    root = tk.Tk()
    jeu = JeuDeDevine(root)
    root.mainloop()

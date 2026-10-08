import tkinter as tk

from guiutils import ValidatedEntry, slug


def actualizar_estado(*_):
    if email.valid:
        lbl_estado.config(
            text="Estado: VÁLIDO",
            fg="green"
        )
    else:
        lbl_estado.config(
            text="Estado: INVÁLIDO",
            fg="red"
        )


def actualizar_slug(*_):
    resultado = slug(entrada.get())

    if resultado:
        lbl_slug.config(text=f"Slug: {resultado}")
    else:
        lbl_slug.config(text="Slug: —")


root = tk.Tk()
root.title("Demo - Componentes reutilizables")
root.geometry("520x300")


titulo = tk.Label(
    root,
    text="Componentes reutilizables",
    font=("Arial", 16, "bold")
)
titulo.pack(pady=15)


# =========================================================
# VALIDACIÓN DE EMAIL
# =========================================================

tk.Label(
    root,
    text="Correo electrónico:"
).pack()


email = ValidatedEntry(
    root,
    pattern=r"^[\w.-]+@[\w.-]+\.[a-zA-Z]{2,}$",
    width=45
)
email.pack(padx=10, pady=5)


lbl_estado = tk.Label(
    root,
    text="Estado: INVÁLIDO",
    fg="red"
)
lbl_estado.pack()


email.bind(
    "<KeyRelease>",
    actualizar_estado
)


# =========================================================
# SLUGIFIER
# =========================================================

tk.Label(
    root,
    text="Texto para convertir a slug:"
).pack(pady=(20, 0))


entrada = ValidatedEntry(
    root,
    pattern=r".+",
    width=45
)
entrada.pack(padx=10, pady=5)


lbl_slug = tk.Label(
    root,
    text="Slug: —"
)
lbl_slug.pack()


entrada.bind(
    "<KeyRelease>",
    actualizar_slug
)


root.mainloop()
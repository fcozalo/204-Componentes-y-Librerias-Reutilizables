import unittest

from guiutils.slugifier import slug


class TestSlugifier(unittest.TestCase):

    def test_hola_mundo(self):
        self.assertEqual(
            slug("Hola Mundo"),
            "hola-mundo"
        )

    def test_acentos(self):
        self.assertEqual(
            slug("Programación Avanzada"),
            "programacion-avanzada"
        )

    def test_simbolos(self):
        self.assertEqual(
            slug("Hola!!! Mundo???"),
            "hola-mundo"
        )

    def test_espacios_multiples(self):
        self.assertEqual(
            slug("Hola     Mundo"),
            "hola-mundo"
        )

    def test_guiones_repetidos(self):
        self.assertEqual(
            slug("Hola---Mundo"),
            "hola-mundo"
        )


if __name__ == "__main__":
    unittest.main()
class Point:
    b = 7
    def __init__(self, x=float('inf'), y=float('inf')):
        self.x = x
        self.y = y

    def copy(self):
        return Point(self.x, self.y)

    def is_zero(self):
        return self.x > 1e20 or self.x < -1e20

    def neg(self):
        return Point(self.x, -self.y)

    def dbl(self):
        if self.is_zero():
            return self.copy()
        try:
            L = (3 * self.x * self.x) / (2 * self.y)
        except ZeroDivisionError:
            return Point()
        x = L * L - 2 * self.x
        return Point(x, L * (self.x - x) - self.y)

    def add(self, q):
        if self.x == q.x and self.y == q.y:
            return self.dbl()
        if self.is_zero():
            return q.copy()
        if q.is_zero():
            return self.copy()
        try:
            L = (q.y - self.y) / (q.x - self.x)
        except ZeroDivisionError:
            return Point()
        x = L * L - self.x - q.x
        return Point(x, L * (self.x - x) - self.y)

    def mul(self, n):
        p = self.copy()
        r = Point()
        i = 1
        while i <= n:
            if i&n:
                r = r.add(p)
            p = p.dbl()
            i <<= 1
        return r

    def __str__(self):
        return "({:.3f}, {:.3f})".format(self.x, self.y)

def show(s, p):
    print(s, "Zero" if p.is_zero() else p.x,p.y)

def from_y(y):
    n = y * y - Point.b
    x = n**(1./3) if n>=0 else -((-n)**(1./3))
    return Point(x, y)

# demonstrate
g = from_y(2)
show("a =", g)

pr_a = 27
pr_b = 29
pub_a = g.mul(pr_a)
pub_b = g.mul(pr_b)
show("pub_a =", pub_a)
show("pub_b =", pub_b)

shared_a = pub_b.mul(pr_a)
shared_b = pub_a.mul(pr_b)
show("shared_a =", shared_a)
show("shared_b =", shared_b)


#!/bin/bash

import sys

class Error(Exception):
    pass

class DimensionMisMatchError(Error):
    pass


class FuzzySet:
    def __init__(self,values):
        self.dict = values

    def display(self):
        for element in self.dict:
            print(element," ",self.dict[element])
        print("\n")

    def __len__(self):
        print("length of fuzzy")
        return len(self.dict)
        
    def union(self,FZ):
        result = dict()

        for element in self.dict:
            if element in FZ.dict: #check if the element is in other dictionary
                #print(element," ",max(self.dict[element],FZ.dict[element]))
                result[element] = max(self.dict[element],FZ.dict[element])
            else:
                #print(element," ",self.dict[element])
                result[element] = self.dict[element]
            
        for element in FZ.dict:
            if element in self.dict: #check if the element is in other dictionary
                #print(element," ",max(self.dict[element],FZ.dict[element]))
                result[element] = max(self.dict[element],FZ.dict[element])
            else:
                #print(element," ",FZ.dict[element])
                result[element] = FZ.dict[element]
                
        return FuzzySet(result)

    def intersection(self,FZ):
        result = dict()

        for element in self.dict:
            if element in FZ.dict: #check if the element is in other dictionary
                #print(element," ",min(self.dict[element],FZ.dict[element]))
                result[element] = min(self.dict[element],FZ.dict[element])
            
        for element in FZ.dict:
            if element in self.dict: #check if the element is in other dictionary
                #print(element," ",min(self.dict[element],FZ.dict[element]))
                result[element] = min(self.dict[element],FZ.dict[element])
        
        return FuzzySet(result)



    def __neg__(self):
        result = dict()
        for element in self.dict:
            result[element] = 1-self.dict[element]
        return FuzzySet(result)


    def difference(self,FZ):
        FZ_compl = -FZ
        return self.intersection(FZ_compl)


    def product(self,FZ):
        for element1 in self.dict:
            for element2 in FZ.dict:
                print(min(self.dict[element1], FZ.dict[element2]), end=" ")
            print()
        print()






class FuzzyRelation:
    def __init__(self,*args):
        if len(args) == 2: 
            FZ1 = args[0]
            FZ2 = args[1]
            ''' tried making FuzzyRelation dict(dict()) but couldn't do the matrix multiplication then. So reverting to [[]] '''
            self.cart = list(list())
            for element1 in FZ1.dict:
                temp = []
                for element2 in FZ2.dict:
                    temp.append(min(FZ1.dict[element1],FZ2.dict[element2]))
                self.cart.append(temp)

        if len(args) == 1:
            self.cart = args[0]


    def display(self):
        for row_index in self.cart:
            for col_index in row_index:
                print(col_index, end=" ")
            print()
        print()

    def get_dimension(self):
        return(len(self.cart),len(self.cart[0]))

    def MaxMinComposition(self,R):
        ''' Check for compatibility ie: matrix dimensions should be such that multiplication should be possible '''
        try:
            dimen1 = self.get_dimension()
            dimen2 = R.get_dimension()
            if dimen1[1] != dimen2[0]:
                raise DimensionMisMatchError
            else:
                result = list(list())
                for i in range(len(self.cart)):
                    max_list = []
                    for j in range(len(R.cart[0])):
                        min_list = []
                        for k in range(len(R.cart)):
                            min_list.append(min(self.cart[i][k] , R.cart[k][j]))
                        max_list.append(max(min_list))
                    result.append(max_list)
                return FuzzyRelation(result)

        except DimensionMisMatchError:
            print("Mismatch in dimensions")
        except:
            print(sys.exc_info())




if __name__ == '__main__':
    FZ1 = FuzzySet({1:0.6,
                    2:0.2,
                    3:0.4,
                    4:0.7})
    print("Fuzzy Set FZ1 => ")
    FZ1.display()
    
    FZ2 = FuzzySet({1:0.1,
                    2:0.5,
                    3:0.3
                    })
    print("Fuzzy Set FZ2 => ")
    FZ2.display()

    FZ3 = FuzzySet({1:0.6,
                    2:0.3,
                    3:0.4
                    })
    print("Fuzzy Set FZ3 => ")
    FZ3.display()

    FZ4 = FuzzySet({3:0.6,
                    4:0.5
                    })
    print("Fuzzy Set FZ4 => ")
    FZ4.display()

    # UNION
    print("FZ1 UNION FZ2 =>")
    FZ1.union(FZ2).display()

    # INTERSECTION
    print("FZ2 INTERSECTION FZ2 =>")
    FZ1.intersection(FZ2).display()
    
    # COMPLEMENT
    print(" FZ1 COMPLEMENT =>")
    n = -FZ1 # Operator overloading on "-" built-in operator . Have to assign result of operation to another variable.
    n.display()
    print(" FZ2 COMPLEMENT =>")
    (-FZ2).display() # The round bracket around -FZ2 are necessary

    # DIFFERENCE => A intersect (B complement)
    print("FZ1 - FZ2 = FZ1 INTERSECTION FZ2 COMPLEMENT => ")
    FZ1.difference(FZ2).display()
    print()
    print("FZ2 - FZ1 = FZ2 INTERSECTION FZ1 COMPLEMENT => ")
    FZ2.difference(FZ1).display()
    print()

    # CARTESIAN PRODUCT
    print("FZ1 X FZ2 => ")
    FZ1.product(FZ2)
    print("FZ2 X FZ1 => ")
    FZ2.product(FZ1)




    # MAXMIN COMPOSITION
    R1 = FuzzyRelation(FZ1,FZ2)
    print("Fuzzy Relation of FZ1 and FZ2 => ")
    R1.display()
    print()

    R2 = FuzzyRelation(FZ3,FZ4)
    print("Fuzzy Relation of FZ3 and FZ4 => ")
    R2.display()
    print()

    print("MAX MIN COMPOSITION OF R1 and R2 => ")
    R1.MaxMinComposition(R2).display()
    







""" 
IMPROVEMENTS :
3) Include a function to append a value to fuzzy_set . Use operator overloading on built-int operator "+"

NOTES
1) Interestingly python doesn't support constructor overloading
"""

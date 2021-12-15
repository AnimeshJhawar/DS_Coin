Exercise 2.1. Collision finding algorithm
In this exercise you need to find collisions to the CRF Fn.
(a) Implementing FindCollDeterministic: Find a collision by starting with a fixed string (hard-coded in
your program), and then repeatedly evaluating the CRF until a collision is found.
(b) Implementing FindCollRandomized: Find a collision by evaluating the function Fn on enough random
points. How many random points? Suppose the function’s co-domain is R = set of all hexadecimal
strings of length outputsize. Then evaluating the function on |R| + 1 distinct inputs will produce a
collision.
What is the time and space complexity of your algorithm? You can assume that computing the CRF
on input string x takes O(|x|) time and space.

Exercise 2.2. ♠ Sign long messages and Verify signatures
For this exercise you need to use the predefined functions BoundedMsgSign and BoundedMsgVerify to sign
and verify arbitrarily long messages.
(a) Implementing Sign: Construct a signature for an arbitrarily long message. For constructing the
signature, you need to first compress the long input message by applying the appropriate CRF i.e.
using a CRF instance with outputsize = 64, and then sign the CRF output using the function
BoundedMsgSign.
(b) Implementing Verify: Verify the signature constructed using your implementation of Sign for an
arbitrarily long message.

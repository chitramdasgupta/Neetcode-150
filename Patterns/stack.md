<!-- TOC -->
* [Stack](#stack)
  * [Min stack](#min-stack)
    * [Problem](#problem)
    * [Solution](#solution)
  * [Evaluate Reverse Polish Notation](#evaluate-reverse-polish-notation)
    * [Problem](#problem-1)
  * [Removing Stars from the String](#removing-stars-from-the-string)
    * [Problem](#problem-2)
  * [Validate stack sequences](#validate-stack-sequences)
  * [Generate parentheses](#generate-parentheses)
    * [Problem](#problem-3)
    * [Solution](#solution-1)
  * [Asteroid collision](#asteroid-collision)
    * [Problem](#problem-4)
    * [Solution](#solution-2)
  * [Daily temperatures](#daily-temperatures)
    * [Problem](#problem-5)
    * [Solution](#solution-3)
  * [Online stock span](#online-stock-span)
    * [Problem](#problem-6)
    * [Solution](#solution-4)
  * [Car fleet](#car-fleet)
  * [Problem](#problem-7)
  * [Solution](#solution-5)
  * [Simplify path](#simplify-path)
    * [Problem](#problem-8)
    * [Solution](#solution-6)
  * [Decode string](#decode-string)
    * [Problem](#problem-9)
    * [Solution](#solution-7)
  * [Remove k digits](#remove-k-digits)
    * [Problem](#problem-10)
    * [Solution](#solution-8)
  * [Remove all adjacent duplicates in string II](#remove-all-adjacent-duplicates-in-string-ii)
    * [Problem](#problem-11)
    * [Solution](#solution-9)
  * [132 pattern](#132-pattern)
    * [Problem](#problem-12)
    * [Solution](#solution-10)
  * [Flatten Nested List Iterator](#flatten-nested-list-iterator)
    * [Problem](#problem-13)
    * [Solution](#solution-11)
  * [Sum of subarray minimums](#sum-of-subarray-minimums)
    * [Problem](#problem-14)
    * [Solution](#solution-12)
<!-- TOC -->

# Stack

## Min stack

### Problem

We want to implement a stack such that all the usual stack operations along with `getMin()` will take `O(1)` time.

### Solution

We maintain two stacks: one usual stack, and another stack that will have the corresponding minimum element at each
position of the stack.

## Evaluate Reverse Polish Notation

### Problem

Evaluate expressions like `2 3 + 5 *`

## Removing Stars from the String

### Problem

`leet**cod*e` to `lecoe`

## Validate stack sequences

Given two integer arrays of unique elements, we have to verify if they are valid `push` and `pop` operations on a stack.

## Generate parentheses

### Problem

Given a number `n`, we have to generate `n` pairs of well-formed parentheses.

### Solution

We don't need a stack for this problem.

The constraints are:

1. We can add an open parentheses as long as `open` < `n`.
2. We can add a closed parentheses when `closed` < `open`.
3. Hence, at each point we can have, at most, two choices: add an open parentheses, or a closing parentheses
4. We stop once `open == n` and `closed == n`.

We have a backtracking problem at hand.

## Asteroid collision

### Problem

We have an array of integers, where each integer represents an asteroid moving in the same line. A positive numbers
means
that the asteroid is moving to the right, and a negative to the left. The value represents the size of the asteroid. All
asteroids are moving with the same speed.

A smaller asteroid colliding with a bigger one means that the smaller one will be destroyed, and two equal-sized
asteroids
colliding with each other means that both of them will be destroyed.

We have to return the final asteroid array.

### Solution

Since, the asteroids are moving in the same line and the same speed, only the asteroids next to each other and moving
in opposite directions will collide with each other.

We can maintain a stack.

Also, an important observation is that a collision can happen only when the top of the stack is positive and the element
we are looking at is negative.

Also, remember that an asteroid can collide with multiple asteroids (one after the other).

## Daily temperatures

### Problem

We are given an array of temperatures, and we are to return an equal-length array which contains the number of days
it took from an index to encounter a greater temperature.

### Solution

When we encounter a temperature while we loop through the temperatures array, we need to figure out if we came across
a lower temperature previously, and at what index.

Since, we are interested in only the lower temperature closest to the given temperature, we can store the temperature
and the corresponding index in a stack.

Thus, when we come across a temperature we compare the previous temperatures in the stack one by one. If it is lower,
we modify the index of the old temperature with the (index of the new temperature - index of old temperature), and
continue doing so if we keep on encountering lower temperatures.

## Online stock span

### Problem

We will be given a price for a stock one by one. We will have to return the span of the given price.

A span is the number of consecutive days for which the stock price was greater than or equal to the given stock price.

We have to return an array of the spans.

Note that, by default the span of each element is 1.

### Solution

For a given stock price, we need a way to find out the closest stock price which was greater than it and its
corresponding span. We can do that by keeping the pair of the price and the span in a stack.

For a given price, we go over the stack from the top and check if the price is greater. If it is, then we pop and
add its span to it. We continue doing this until we run out of elements in the stack, or we encounter an element that
is smaller than the given price.

We then append the given price and the span to the stack.

The key insight: for a given stock price, we don't have to check all the previous stock prices. Span is the number of
*consecutive* days. If the last stock price is lower, we simply ignore, else we add its span to the current span and
continue doing so for the previous stock prices.

## Car fleet

## Problem

We have `n` cars positioned at `position[i]`, and moving at `speed[i]` at a distance at `target` units away. All the
cars are moving in the same direction and along the same line.

When a card collides with the one ahead, its speed and position becomes equal to it.

Multiple such cars with the same speed and position is called a fleet. Note that a single car can also be considered a
fleet. Also, cars arriving at the destination at the same time is also a fleet.

We have to return the number of fleets that will arrive at the destination.

## Solution

We sort the array of pairs (`position` and `speed`) in the descending order of `position`. We then consider this array
as a stack.

Then we iterate from the last to the first car. For each car we calculate the time. If the time is lesser than the
slowest car, so far, we pop that car (as it will *not* collide and make a fleet), and continue with the previous cars.

## Simplify path

### Problem

We are given an *absolute* path to a file or directory, and we need to return the simplified canonical path.

The canonical path should:

1. Start with a '/'
2. Not end with a trailing '/'
3. Should only contain the directories from the root to the target (no '.', or '..')

### Solution

We need to maintain a stack to handle the "go-out" scenario with `..`, as it would translate to a pop operation.

We build the directories character by character by looping over the path, and then push the `directory` to the stack.

We return the stack joined by the `/` and beginning with a `/`.

## Decode string

### Problem

We are given an encoded string of the form: `2[ab]`, and we have to return the decoded string: `abab`.

The encodings can be nested as well: `2[ab3[c]]`.

### Solution

Since, problems with nested parentheses are easy to solve with stacks, we will loop over the string and append to the
stack character by character. When we encounter a `]`, we pop till we get to the `k` number, and then we push back
the decoded string `k * string`.

## Remove k digits

### Problem

We are given a string representation of a number, and a number `k`. We have to remove `k` characters from the string
so that we end up with the smallest possible number.

### Solution

We start by figuring out the intuition behind removing the digits to arrive at a pattern. Then, we try to implement an
algorithm to carry out the pattern efficiently.

We see than when we have contiguous digits, removing the larger one (either in the start or the end) gives the smallest
number.

Also, when the digits are non-contiguous, removing the larger digit from the start (as opposed to the same digit) in the
end, leads to the correct answer.

So, we are biased to removing the larger digit and from the left.

If we have a number in decreasing order, `54321`, we will remove from the left as many times as we can.

If we have a number in increasing order, `12345`, we will remove from the right as many times as we can.

The pattern emerges that we want to keep a monotonically increasing sequence of numbers. We can do that with a
monotonically increasing (or equal) stack.

## Remove all adjacent duplicates in string II

### Problem

We are given a string which may contain adjacent duplicate characters, and a number `k`.

We have to return a string after removing all `k` length adjacent duplicate characters as many times as possible.

### Solution

The thing to notice is that the order in which we do the removals does not matter.

We will always remove from the most recently encountered characters. This is reminiscent of a stack.

We will maintain a stack of pairs (character, frequency). When we encounter a new character that is not the same as the
top, we push to the stack. When the new character is same, we do not append it; instead, we increment the frequency of
the top of the stack character. When the frequency reaches `k`, we pop the pair.

## 132 pattern

### Problem

Given an array of numbers, we have to check if there is an 132 pattern, which is a subsequence of 3 numbers such that
`i < j < k`, and `nums[i] < nums[k] < nums[j]`.

### Solution

When we loop over the numbers, we get a potential `k` candidate. To find out the `j` candidate, we need a number
encountered previously that is greater than `k`. Now, to find out the `i` candidate we need a number encountered
previously to `j` that is smaller than both the `j` and `k` candidate.

This can be done using a monotonically decreasing stack, where we maintain the number and the corresponding `minLeft` (
that is the minimum number encountered to the left to the current element).

## Flatten Nested List Iterator

### Problem

We are given a list of elements, where each element is an integer of a list of elements (again, the element can be an
integer, or a list).

We need to implement the given interface so that we can return the next element, and we can return if there are any more
elements.

### Solution

We will maintain a stack for the flattened integer values of the given array.

We backtrack over the given list, and append if we come across an integer, else we continue with the recursion.

## Sum of subarray minimums

### Problem

We are given an array, and we need to find the sum of the minimums of all the possible subarrays.

### Solution


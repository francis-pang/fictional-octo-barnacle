package provide hashTable 1.0

namespace eval hashTable {
  # Although this might not be correct, but this is the easiest implementation, we store the hash table as a expanding even-numbered list
  variable listHashTable [list]

  # Initialise the hash table list
  # Set with size 2
  set listHashTable [list 1 {}]
}

proc hashTable::containsKey​ {key} {
  return {Not implemented yet}
}

proc hashTable::containsValue​ {value} {
  return {Not implemented yet}
}

proc hashTable::get​ {key} {
  return {Not implemented yet}
}

proc hashTable::isEmpty {} {
  variable listHashTable
  for {set i 1} {$i < [llength $listHashTable]} {incr i 2} {
    if {[lindex $listHashTable $i] != {}} {
      return false
    }
  }
  return true
}

proc hashTable::put​ {key value} {

}

proc hashTable::remove​ {key} {
  return {Not implemented yet}
}

proc hashTable::size {} {
  variable listHashTable
  set HashTableSize 0
  for {set i 1} {$i < [llength $listHashTable]} {incr i 2} {
    if {[lindex $listHashTable $i] != {}} {
      incr HashTableSize
    }
  }
  return $HashTableSize
}

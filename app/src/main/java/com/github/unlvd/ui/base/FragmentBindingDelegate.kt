package com.github.unlvd.ui.base

import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.viewbinding.ViewBinding
import java.lang.IllegalStateException
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

class FragmentBindingDelegate<T: ViewBinding>(
  val fragment: Fragment,
  val bindingFactory: (View) -> T
): ReadOnlyProperty<Fragment, T> {

  private var binding: T? = null

  init {
    fragment.lifecycle.addObserver(object : DefaultLifecycleObserver{
      override fun onCreate(owner: LifecycleOwner) {
        fragment.viewLifecycleOwnerLiveData.observe(fragment){
          it -> it.lifecycle.addObserver(object :DefaultLifecycleObserver{
          override fun onDestroy(owner: LifecycleOwner) {
            binding = null
          }
          })
        }
      }
    })
  }

  override fun getValue(thisRef: Fragment, property: KProperty<*>): T {
    val binding = binding
    if(binding != null){
      return binding
    }
    val lifecycle = fragment.viewLifecycleOwner.lifecycle
    if(!lifecycle.currentState.isAtLeast(Lifecycle.State.INITIALIZED)){
      throw IllegalStateException("Error")
    }
    return bindingFactory.invoke(thisRef.requireView()).also { this@FragmentBindingDelegate.binding = it }
  }
}
fun<T: ViewBinding> Fragment.viewBinding(viewBindingFactory: (View)-> T) =
  FragmentBindingDelegate(this,viewBindingFactory)